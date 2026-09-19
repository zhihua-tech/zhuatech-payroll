/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.payroll.controller;import cn.zhuatech.payroll.common.ApiResponse;import cn.zhuatech.payroll.service.PayrollCalculationService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/advanced/payroll") public class PayrollCalculationController{private final PayrollCalculationService service;/**
                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                    */
public PayrollCalculationController(PayrollCalculationService service){this.service=service;}/**
                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                 */
@PostMapping("/calculate") public ApiResponse<PayrollCalculationService.PayrollResult> calculate(@Valid @RequestBody PayrollCalculationService.PayrollRequest request){return ApiResponse.ok(service.calculate(request));}}
