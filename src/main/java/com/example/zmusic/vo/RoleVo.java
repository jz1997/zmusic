package com.example.zmusic.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVo extends BaseVo {
  private String name;

  private String title;
}
