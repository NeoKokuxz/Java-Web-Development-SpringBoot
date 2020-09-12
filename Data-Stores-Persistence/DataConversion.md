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

## PlantController.java
```java
@RestController
@RequestMapping("/plant")
public class PlantController {

   @Autowired
   private PlantService plantService;

   public PlantDTO getPlantDTO(String name){
       return convertPlantToPlantDTO(plantService.getPlantByName(name));
   }

   @JsonView(Views.Public.class)
   public Plant getFilteredPlant(String name){
       return plantService.getPlantByName(name);
   }

   private PlantDTO convertPlantToPlantDTO(Plant plant){
       PlantDTO plantDTO = new PlantDTO();
       BeanUtils.copyProperties(plant, plantDTO);
       return plantDTO;
   }
}
```
## Views.java
```java
public class Views {
    public interface Public {}
}
```

## Plant.java
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
   @Id
   @GeneratedValue
   private Long id;

   @JsonView(Views.Public.class)
   @Nationalized // should use @Nationalized instead of @Type=nstring
   private String name;

   @JsonView(Views.Public.class)
   @Column(precision=12, scale=4)
   private BigDecimal price; // BigDecimal is the standard Java class for currency math

   @ManyToOne //many plants can belong to one delivery
   @JoinColumn(name = "delivery_id")  //map the join column in the plant table
   private Delivery delivery;

   /* getters and setters*/
}
```
## PlantDTO.java
```java
public class PlantDTO {
   private String name;
   private BigDecimal price;

   /* getters and setters */
}
```
