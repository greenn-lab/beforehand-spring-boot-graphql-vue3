package beforehand.springboot.graphql.server.infrastructure.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanToCharYNConverter
    implements AttributeConverter<Boolean, Character> {

  @Override
  public Character convertToDatabaseColumn(Boolean attribute) {
    return attribute == null || !attribute ? 'N' : 'Y';
  }

  @Override
  public Boolean convertToEntityAttribute(Character dbData) {
    return dbData != null && dbData == 'Y';
  }
}
