package com.example.zmusic.repository.specs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCriteria {
  private String key;
  private Object value;
  private SearchOperation operation;
}
