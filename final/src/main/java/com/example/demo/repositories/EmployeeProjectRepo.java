package com.example.demo.repositories;

import com.example.demo.models.EmployeeProject;
import com.example.demo.services.CSVReader;
import com.example.demo.services.CSVWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeProjectRepo {
    private final JdbcTemplate jdbcTemplate;
    private final CSVReader reader = new CSVReader();
    private final CSVWriter writer = new CSVWriter();

    private final String filePath = "static/employee_projects.csv";
    private final String absoluteFilePath = "C:\\Users\\my\\IdeaProjects\\Staff\\Staff\\final\\src\\main\\resources\\static\\employee_projects.csv";

    public EmployeeProjectRepo (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addEmployeeProject(Serializable emp) {
        if(emp instanceof EmployeeProject) {
            String sql = "INSERT INTO employee_projects (employee_id, project_id, from_date, to_date) " +
                    "VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, ((EmployeeProject) emp).getEmployeeId(), ((EmployeeProject) emp).getProjectId(),
                    ((EmployeeProject) emp).getFromDate(), ((EmployeeProject) emp).getToDate());
        }
    }

    public List<? extends Serializable> getAllEmployeeProjects() {
        //TODO fix path and create run configuration
        // Make it so it reads from the DB to we have real-time updated records
        return reader.read(absoluteFilePath);
    }

   public void addRecordsToDB () {
        String sql = "INSERT INTO employee_projects (employee_id, project_id, from_date,to_date)" +
                "VALUES (?,?,?,?)";
        List<? extends Serializable> empProjects = getAllEmployeeProjects();
        try {
            cleanDbTable();
            for (Serializable emp : empProjects) {
                if(emp instanceof EmployeeProject) {
                    jdbcTemplate.update(sql, ((EmployeeProject) emp).getEmployeeId(), ((EmployeeProject) emp).getProjectId(),
                            ((EmployeeProject) emp).getFromDate(), ((EmployeeProject) emp).getToDate());
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
   }

   public void cleanDbTable() {
        String sql = "TRUNCATE employee_projects";
        jdbcTemplate.execute(sql);
   }


    public StringBuilder executeOverlappingDays () {
        String sql = "WITH EmployeePairs AS ( " +
                "    SELECT " +
                "        ep1.employee_id AS employee_id_1, " +
                "        ep2.employee_id AS employee_id_2, " +
                "        ep1.project_id AS project_id, " +
                "        SUM( " +
                "            GREATEST( " +
                "                LEAST(COALESCE(ep1.to_date, CURRENT_DATE), COALESCE(ep2.to_date, CURRENT_DATE)) - " +
                "                GREATEST(ep1.from_date, ep2.from_date) + 1, " +
                "                0 " +
                "            ) " +
                "        ) AS total_overlap_days " +
                "    FROM " +
                "        employee_projects ep1 " +
                "    INNER JOIN " +
                "        employee_projects ep2 ON ep1.project_id = ep2.project_id " +
                "    WHERE " +
                "        ep1.employee_id < ep2.employee_id " +
                "        AND ep1.from_date <= COALESCE(ep2.to_date, CURRENT_DATE) " +
                "        AND COALESCE(ep1.to_date, CURRENT_DATE) >= ep2.from_date " +
                "    GROUP BY " +
                "        ep1.employee_id, ep2.employee_id, ep1.project_id " +
                ") " +
                "SELECT " +
                "    employee_id_1, " +
                "    employee_id_2, " +
                "    project_id, " +
                "    SUM(total_overlap_days) AS overlapping_days_on_project " +
                "FROM " +
                "    EmployeePairs " +
                "GROUP BY " +
                "    employee_id_1, employee_id_2, project_id " +
                "ORDER BY " +
                "    overlapping_days_on_project DESC";

        StringBuilder sb = new StringBuilder();

        jdbcTemplate.query(sql, (resultSet) -> {
            long employeeId1 = resultSet.getLong("employee_id_1");
            long employeeId2 = resultSet.getLong("employee_id_2");
            long projectId = resultSet.getLong("project_id");
            long overlappingDays = resultSet.getLong("overlapping_days_on_project");

            String result = (employeeId1 +
                    ", " + employeeId2 +
                    ", " + projectId +
                    ", " + overlappingDays);

            sb.append(result);
        });
        return sb;
    }

    public void saveRecordsToCSV() {
        List<EmployeeProject> recordsList = new ArrayList<>();
        String sql = "SELECT * FROM employee_projects";
        jdbcTemplate.query(sql, (resultSet) -> {
            while(resultSet.next()) {
                EmployeeProject emp = new EmployeeProject();
                emp.setEmployeeId(Long.parseLong(resultSet.getString("employee_id")));
                emp.setProjectId(Long.parseLong(resultSet.getString("project_id")));
                emp.setFromDate(LocalDate.parse(resultSet.getString("from_date")));
                emp.setToDate(LocalDate.parse(resultSet.getString("to_date")));
                recordsList.add(emp);
            }
        });
        writer.write(recordsList, absoluteFilePath);
    }
}
