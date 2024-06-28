package com.advanced.comidinhasveganas.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.advanced.comidinhasveganas.strategy.PrecoStrategy;
import com.advanced.comidinhasveganas.strategy.PrecoNormalStrategy;
import com.advanced.comidinhasveganas.strategy.PrecoFechadoStrategy;
import java.util.List;

@Converter
public class PrecoStrategyConverter implements AttributeConverter<PrecoStrategy, String> {

  @Override
  public String convertToDatabaseColumn(PrecoStrategy attribute) {
    if (attribute instanceof PrecoNormalStrategy) {
      return "normal";
    } else if (attribute instanceof PrecoFechadoStrategy) {
      return "fechado";
    } else {
      throw new IllegalArgumentException("Tipo de estratégia desconhecido.");
    }
  }

  @Override
  public PrecoStrategy convertToEntityAttribute(String dbData) {
    if ("normal".equalsIgnoreCase(dbData)) {
      return new PrecoNormalStrategy(List.of()); // ou passe uma lista de itens real
    } else if ("fechado".equalsIgnoreCase(dbData)) {
      return new PrecoFechadoStrategy();
    } else {
      throw new IllegalArgumentException("Tipo de estratégia desconhecido.");
    }
  }
}
