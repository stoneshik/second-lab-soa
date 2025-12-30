package lab.soa.repositories.specifications.flat;

import lab.soa.repositories.specifications.FieldName;

/*
* все значения по которым есть фильтрация
* id
* name
* coordinates.id
* coordinates.x
* coordinates.y
* creationDate
* area
* numberOfRooms
* height
* view
* transport
* house.id
* house.name
* house.year
* house.numberOfFlatsOnFloor
*/
public enum FlatFieldNames implements FieldName {
    ID("id"),
    NAME("name"),
    COORDINATES_ID("coordinates.id"),
    COORDINATES_X("coordinates.x"),
    COORDINATES_Y("coordinates.y"),
    CREATION_DATE("creationDate"),
    AREA("area"),
    NUMBER_OF_ROOMS("numberOfRooms"),
    HEIGHT("height"),
    VIEW("view"),
    TRANSPORT("transport"),
    HOUSE_ID("house.id"),
    HOUSE_NAME("house.name"),
    HOUSE_YEAR("house.year"),
    HOUSE_NUMBER_OF_FLATS_ON_FLOOR("house.numberOfFlatsOnFloor"),
    ;

    private final String fieldName;

    private FlatFieldNames(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
