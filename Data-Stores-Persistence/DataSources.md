# Data Sources

## Data Source vs. DataSource
- Data source
  - A source of data, such as database
- DataSource
  - Java utility class that manages connections to physical or logical databases
- In particular, Spring injects a Hikari DataSource by default for JDBC connections, which is a lightweight JDBC DataSource that supports connection pooling.

### DataSource
> Java utility class that manages connections to physical or logical databases

#### Basic DataSource
> Produces Connection objects

#### Connection-Pooling DataSource
> Pools Connection objects to maintain a list of open connections and reuse closed connections

#### Distributed Transaction DataSource
> Uses transaction manager to manage distributed requests oftern pools connections as well

## Database URL structure
- jdbc:mysql://localhost:3306/exampledb
- subprotocol // serverName[:port] / databaseInstanceName: properties



