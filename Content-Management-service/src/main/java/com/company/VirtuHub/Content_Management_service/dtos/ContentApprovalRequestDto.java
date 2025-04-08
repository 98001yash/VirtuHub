package com.company.VirtuHub.Content_Management_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentApprovalRequestDto {


    private Long contentId;
    private boolean isApproved;
    private String rejectionReason;
}
