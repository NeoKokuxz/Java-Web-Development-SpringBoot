# Data Conversion

## Data Transfer Objects (DTOs)
- Data structures designed to represent the needs of the front end.
### Data Transfer Object(DTO) Purposes
- Simplify and document interaction between front end and controller
- conceal database structures
- Limit the amount of data exchanged
- Customize display data to meet the needs of front end


## JSONView Annotation
- Filters which Entity data is visible to the presentation layer.
- Quickly specify which parts of Entities should be visible to which consumer.
- Often a simple choice when controlling full stack.
- Not as helpful when you need to combine data from multiple Entities.
- Can require Entity updates if front end needs change.
- Often grouped together in a Views class, containing interfaces such as ‘Public’, ‘Private’, or interfaces named for specific endpoint recipients.
