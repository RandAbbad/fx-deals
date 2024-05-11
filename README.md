# Clustered Data Warehouse

Clustered Data Warehouse is a project developed as part of a Scrum team for Bloomberg to analyze FX deals. 
The primary goal of this project is to accept deal details from various sources and persist them into a database. 


### Request Logic

The project handles incoming FX deals requests with the following key fields:

- Deal Unique Id
- From Currency ISO Code "Ordering Currency"
- To Currency ISO Code
- Deal Timestamp
- Deal Amount in Ordering Currency

### System Features

- Preventing duplicate FX deal imports
- Ensuring transaction integrity with non-reversible operations
- Robust error and exception handling, including validation of row structure (e.g., missing fields, type format)
- Dockerized deployment environment
- Maven or Gradle project structure
- Comprehensive logging system
- Unit testing with code coverage
- GitHub hosting for collaboration and version control

## Getting Started

To get started with the Clustered Data Warehouse project:

1. Clone the Project repository from GitHub.

    ```
    git clone https://github.com/RandAbbad/fx-deals.git
    ```



2. Build the Project using Maven by running the following command.

    ```
    mvn clean install package
    ```


3. Run the Application with Docker Compose.

    ```
    docker-compose up
    ```
