package com.mislbd.ababil.foreignremittance.configuration;

import com.mislbd.ababil.approvalflow.annotation.EnableAbabilApprovalflow;
import com.mislbd.ababil.asset.annotation.EnableAbabilAsset;
import com.mislbd.ababil.contacts.annotation.EnableAbabilContacts;
import com.mislbd.ababil.security.policy.client.annotation.EnableAbabilPolicy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("AbabilForeignRemittanceStartupConfiguration")
@ComponentScan("com.mislbd")
@EnableAbabilAsset
@EnableAbabilContacts
@EnableAbabilApprovalflow
@EnableAbabilPolicy
public class StartupConfiguration {}
