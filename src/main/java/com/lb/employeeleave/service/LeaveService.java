package com.lb.employeeleave.service;

import com.lb.employeeleave.dto.LeaveDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

public interface LeaveService {

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    Page<LeaveDTO> getAllEmployeeLeaves(Pageable pageable);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    LeaveDTO getEmployeeLeaveById(Long id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    LeaveDTO createEmployeeLeave(LeaveDTO leaveDTO);

    // only employee if leave request is still pending
    @PreAuthorize("#leaveDTO.employeeDTO.employeeId == authentication.principal.id ")
    LeaveDTO updateEmployeeLeave(LeaveDTO leaveDTO);

    // only admin and EmployeeSupervisor
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    LeaveDTO approveEmployeeLeave(LeaveDTO leaveDTO);

    // only employee on their own pending leave request
    @PreAuthorize("#id == authentication.principal.id ")
    LeaveDTO ChangeEmployeeLeaveStatus(Long id);
}
