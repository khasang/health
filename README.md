# Health

# Swagger use instructions:
build.gradle file includes next dependencies for supporting swagger in this project:
 -  compile group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
 -  compile group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'
 -  compile group: 'io.springfox', name: 'springfox-bean-validators', version: '2.9.2'
 
SwaggerConfig is the controller used to enable swagger and to define it's settings. 
 ## Model annotations
Here annotations related to model or entity:
### @ApiModel
<b>Example:</b><br/>
```java
@ApiModel(value = "Inspection", description = "Class representing an inspection of patient.")
public class Inspection {}
```
### @ApiModelProperty
<b>Example:</b><br/>
```java
@ApiModelProperty(notes = "Unique identifier of inspection", required = true, example = "1", position = 1)
private long id;
```
## Controller annotations
Here annotations related to rest controller:
### @Api
<b>Example:</b><br/>
```java
@Api
public class InspectionController {}
```
### @ApiOperation
<b>Example:</b><br/>
```java
@ApiOperation(value = "Get inspection by id", notes = "Provide an id to get specific inspection", response = Inspection.class)
public Inspection getInspection(@ApiParam(value = "Id of inspection to get. Cannot be empty", required = true) @PathVariable long id) {}
```
### @ApiParam
<b>Example:</b><br/>
```java
public Inspection getInspection(@ApiParam(value = "Id of inspection to get. Cannot be empty", required = true) @PathVariable long id) {}
```
