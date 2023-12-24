# Staff Projects Application
<h4>The goal of this application is to help companies track their projects and who has been working on them.</h3>

<ul>
<b>Prerequisites</b>
    <li>CSV file in the following format with headers :</li>
        <ul>
            <li>Employee ID</li>
            <li>Project ID</li>
            <li>DateFrom</li>
            <li>DateTo</li>
        </ul>
</ul>

<ul>
    <b>Current stage of supported endpoints</b>
        <li>Running the application will delete all previous records in the DB and populate the ones from the CSV file</li>
        <li>http://localhost:8080/emp-projects/all - showing all the CSV records</li>
        <li>http://localhost:8080/emp-projects/add-emp-projects - POST request with a JSON to add employee-project info</li>
        <li>http://localhost:8080/emp-projects/get-overlapping - GET request showing all the employees and projects that worked together</li>
        <li>Closing the application will save all the records and updates made throughout the session in the DB</li>
</ul>

The idea of the project was to get the most amount of days that two employees have worked on different projects, however at this stage this is not supported in this project.
We have a list of employee pairs and the amount they worked together on a certain project, and this info is ordered by the days they have worked together in descending order.




