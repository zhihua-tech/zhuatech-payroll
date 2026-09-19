/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.payroll.controller;

import cn.zhuatech.payroll.common.ApiResponse;
import cn.zhuatech.payroll.service.OffCyclePayrollAuthorizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/payroll")
public class OffCyclePayrollAuthorizationController {
    private final OffCyclePayrollAuthorizationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public OffCyclePayrollAuthorizationController(OffCyclePayrollAuthorizationService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/off-cycle-authorization")
    public ApiResponse<OffCyclePayrollAuthorizationService.Assessment> assess(
            @Valid @RequestBody OffCyclePayrollAuthorizationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
