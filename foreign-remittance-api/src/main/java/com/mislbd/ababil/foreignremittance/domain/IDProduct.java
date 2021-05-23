package com.mislbd.ababil.foreignremittance.domain;

import com.mislbd.ababil.asset.annotation.ProductCode;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class IDProduct implements Serializable {

  private long id;

  @Pattern(regexp = "^[\\p{L} .'-]+$", message = "IDProduct name format is not valid")
  @NotBlank(message = "IDProduct name is required")
  private String name;

  @ProductCode
  @NotNull(message = "Code is required")
  private String code;


  private Long productGLId;
  private String productGLCode;
  private Long exchangeGainGLId;
  private String exchangeGainGLCode;
  private List<String> currencies;
}
