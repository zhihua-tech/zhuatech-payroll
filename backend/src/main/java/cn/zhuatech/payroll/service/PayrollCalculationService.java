/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.payroll.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.math.*;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class PayrollCalculationService{
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public PayrollResult calculate(@Valid PayrollRequest request){BigDecimal gross=request.earnings().stream().map(Earning::amount).reduce(BigDecimal.ZERO,BigDecimal::add);BigDecimal taxable=request.earnings().stream().filter(Earning::taxable).map(Earning::amount).reduce(BigDecimal.ZERO,BigDecimal::add);BigDecimal preTax=request.deductions().stream().filter(Deduction::preTax).map(Deduction::amount).reduce(BigDecimal.ZERO,BigDecimal::add);BigDecimal deductions=request.deductions().stream().map(Deduction::amount).reduce(BigDecimal.ZERO,BigDecimal::add);BigDecimal base=taxable.subtract(preTax).max(BigDecimal.ZERO),remaining=base,previous=BigDecimal.ZERO,tax=BigDecimal.ZERO;List<TaxLine>taxLines=new ArrayList<>();
  for(TaxBracket bracket:request.taxBrackets().stream().sorted(Comparator.comparing(TaxBracket::upToAmount)).toList()){BigDecimal width=bracket.upToAmount().subtract(previous);if(width.signum()<=0)continue;BigDecimal amount=remaining.min(width);if(amount.signum()>0){BigDecimal lineTax=amount.multiply(bracket.rate()).setScale(2,RoundingMode.HALF_UP);taxLines.add(new TaxLine(previous,bracket.upToAmount(),amount,bracket.rate(),lineTax));tax=tax.add(lineTax);remaining=remaining.subtract(amount);}previous=bracket.upToAmount();if(remaining.signum()==0)break;}
  BigDecimal net=gross.subtract(deductions).subtract(tax).setScale(2,RoundingMode.HALF_UP);List<String>warnings=new ArrayList<>();if(remaining.signum()>0)warnings.add("应税所得超过已配置税率区间: "+remaining);if(net.signum()<0)warnings.add("实发金额为负，必须人工复核");String status=net.signum()<0?"REVIEW_REQUIRED":remaining.signum()>0?"TAX_TABLE_GAP":"CALCULATED";return new PayrollResult(status,request.employeeNo(),gross.setScale(2,RoundingMode.HALF_UP),base.setScale(2,RoundingMode.HALF_UP),tax.setScale(2,RoundingMode.HALF_UP),deductions.setScale(2,RoundingMode.HALF_UP),net,taxLines,warnings);
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record PayrollRequest(@NotBlank String employeeNo,@NotBlank String currency,@NotEmpty List<@Valid Earning>earnings,List<@Valid Deduction>deductions,@NotEmpty List<@Valid TaxBracket>taxBrackets){/**
                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                           */
public PayrollRequest{deductions=deductions==null?List.of():List.copyOf(deductions);}}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Earning(@NotBlank String code,@NotNull @DecimalMin("0") BigDecimal amount,boolean taxable){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Deduction(@NotBlank String code,@NotNull @DecimalMin("0") BigDecimal amount,boolean preTax){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record TaxBracket(@NotNull @DecimalMin("0.01") BigDecimal upToAmount,@NotNull @DecimalMin("0") @DecimalMax("1") BigDecimal rate){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record TaxLine(BigDecimal fromExclusive,BigDecimal toInclusive,BigDecimal taxableAmount,BigDecimal rate,BigDecimal tax){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record PayrollResult(String status,String employeeNo,BigDecimal grossPay,BigDecimal taxableBase,BigDecimal tax,BigDecimal deductions,BigDecimal netPay,List<TaxLine>taxLines,List<String>warnings){}
}
