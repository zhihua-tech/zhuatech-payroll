/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.payroll.controller;
import cn.zhuatech.payroll.common.ApiResponse;import cn.zhuatech.payroll.service.PayrollRunReleaseGovernanceService;
import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/payroll")
public class PayrollRunReleaseGovernanceController{private final PayrollRunReleaseGovernanceService service;/**
                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                             */
public PayrollRunReleaseGovernanceController(PayrollRunReleaseGovernanceService service){this.service=service;}/**
                                                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                            */
@PostMapping("/run-release")public ApiResponse<PayrollRunReleaseGovernanceService.Assessment> assess(@Valid @RequestBody PayrollRunReleaseGovernanceService.Request request){return ApiResponse.ok(service.assess(request));}}
