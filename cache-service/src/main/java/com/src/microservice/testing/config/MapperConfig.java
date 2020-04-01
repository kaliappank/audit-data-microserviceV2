package com.src.microservice.testing.config;

import com.src.microservice.testing.dto.UpdateInterfaceDto;
import com.src.microservice.testing.dto.UpdateProjectDto;
import com.src.microservice.testing.dto.UpdateSystemDto;
import com.src.microservice.testing.dto.UpdateAuditDto;
import com.src.microservice.testing.dto.UpdateAuditDataDto;
import com.src.microservice.testing.dto.CreateSystemDto;
import com.src.microservice.testing.dto.CreateInterfaceDto;
import com.src.microservice.testing.dto.CreateAuditDto;
import com.src.microservice.testing.dto.CreateAuditDataDto;
import com.src.microservice.testing.dto.SystemDto;
import com.src.microservice.testing.dto.InterfaceDto;
import com.src.microservice.testing.dto.AuditDto;
import com.src.microservice.testing.dto.AuditDataDto;
import com.src.microservice.testing.model.Interface;
import com.src.microservice.testing.model.Project;
import com.src.microservice.testing.model.System;
import com.src.microservice.testing.model.Audit;
import com.src.microservice.testing.model.AuditData;
import com.src.microservice.testing.service.InterfaceService;
import com.src.microservice.testing.service.ProjectService;
import com.src.microservice.testing.service.SystemService;
import com.src.microservice.testing.service.AuditService;
import com.src.microservice.testing.service.AuditDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class MapperConfig {
	private final InterfaceService interfaceService;
	private final ProjectService projectService;
	private final SystemService systemService;
	private final AuditService auditService;
	private final AuditDataService auditDataService;
	

	public MapperConfig(InterfaceService interfaceService, ProjectService projectService, SystemService systemService, AuditService auditService, AuditDataService auditDataService) {
		this.interfaceService = interfaceService;
		this.projectService = projectService;
		this.systemService = systemService;
		this.auditService = auditService;
		this.auditDataService = auditDataService;
	}
	
	@Bean
	MapperFactory mapperFactory() {
		DefaultMapperFactory defaultMapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).build();
		
		//Interface
		//defaultMapperFactory.classMap(UpdateInterfaceDto.class, Interface.class).mapNulls(false).byDefault().register();

		//Project
		defaultMapperFactory.classMap(UpdateProjectDto.class, Project.class).mapNulls(false).byDefault().register();
		
		//SYstem
		defaultMapperFactory.classMap(CreateSystemDto.class, System.class)
			.byDefault()
			.customize(new CustomMapper<CreateSystemDto, System>() {
				@Override
				public void mapAtoB(CreateSystemDto createSystemDto, System system, MappingContext context){
					super.mapAtoB(createSystemDto, system, context);
					Project project = projectService.validateAndGetProject(createSystemDto.getProject_id());
					system.setProject(project);
				}
			}).register();
			
		defaultMapperFactory.classMap(UpdateSystemDto.class, System.class)
			.mapNulls(false)
			.byDefault()
			.customize(new CustomMapper<UpdateSystemDto, System>(){
				@Override
				public void mapAtoB(UpdateSystemDto updateSystemDto, System system, MappingContext context){
					super.mapAtoB(updateSystemDto, system, context);
					Long dtoProjectId = updateSystemDto.getProject_id();
					if(dtoProjectId != null){
						Project project = projectService.validateAndGetProject(dtoProjectId);
						system.setProject(project);
					}
				}
			}).register();

		defaultMapperFactory.classMap(System.class, SystemDto.class).field("project.project_id", "project_id").byDefault().register();
		
		//INterface
		defaultMapperFactory.classMap(CreateInterfaceDto.class, Interface.class)
			.byDefault()
			.customize(new CustomMapper<CreateInterfaceDto, Interface>() {
				@Override
				public void mapAtoB(CreateInterfaceDto createInterfaceDto, Interface interface1, MappingContext context){
					super.mapAtoB(createInterfaceDto, interface1, context);
					System system = systemService.validateAndGetSystem(createInterfaceDto.getSystem_id());
					interface1.setSystem(system);
				}
			}).register();

		defaultMapperFactory.classMap(UpdateInterfaceDto.class, Interface.class)
			.mapNulls(false)
			.byDefault()
			.customize(new CustomMapper<UpdateInterfaceDto, Interface>(){
				@Override
				public void mapAtoB(UpdateInterfaceDto updateInterfaceDto, Interface interface1, MappingContext context){
					super.mapAtoB(updateInterfaceDto, interface1, context);
					Long dtoSystemId = updateInterfaceDto.getSystem_id();
					if(dtoSystemId != null){
						System system = systemService.validateAndGetSystem(dtoSystemId);
						interface1.setSystem(system);
					}
				}
			}).register();
		
		defaultMapperFactory.classMap(Interface.class, InterfaceDto.class).field("system.system_id", "system_id").byDefault().register();
		
		//Audit - INterface
		defaultMapperFactory.classMap(CreateAuditDto.class, Audit.class)
			.byDefault()
			.customize(new CustomMapper<CreateAuditDto, Audit>() {
				@Override
				public void mapAtoB(CreateAuditDto createAuditDto, Audit audit, MappingContext context){
					super.mapAtoB(createAuditDto, audit, context);
					Interface interface1 = interfaceService.validateAndGetInterface(createAuditDto.getInterface_id());
					audit.setInterface1(interface1);
					System srcSystem = systemService.validateAndGetSystem(createAuditDto.getSource_system_id());
					audit.setSrcSystem(srcSystem);
					System tgtSystem = systemService.validateAndGetSystem(createAuditDto.getTarget_system_id());
					audit.setTgtSystem(tgtSystem);
					Project project = projectService.validateAndGetProject(createAuditDto.getProject_id());
					audit.setProject(project);
				}
			}).register();
		
		defaultMapperFactory.classMap(UpdateAuditDto.class, Audit.class)
			.mapNulls(false)
			.byDefault()
			.customize(new CustomMapper<UpdateAuditDto, Audit>(){
				@Override
				public void mapAtoB(UpdateAuditDto updateAuditDto, Audit audit, MappingContext context){
					super.mapAtoB(updateAuditDto, audit, context);
					Long dtoInterfaceId = updateAuditDto.getInterface_id();
					Long dtoSourceSystemId = updateAuditDto.getSource_system_id();
					Long dtoTargetSystemId = updateAuditDto.getTarget_system_id();
					Long dtoProjectId = updateAuditDto.getProject_id();
					if(dtoInterfaceId != null){
						Interface interface1 = interfaceService.validateAndGetInterface(dtoInterfaceId);
						audit.setInterface1(interface1);
					}
					if(dtoSourceSystemId != null){
						System srcSystem = systemService.validateAndGetSystem(dtoSourceSystemId);
						audit.setSrcSystem(srcSystem);
					}
					if(dtoTargetSystemId != null){
						System tgtSystem = systemService.validateAndGetSystem(dtoTargetSystemId);
						audit.setTgtSystem(tgtSystem);
					}
					if(dtoProjectId != null){
						Project project = projectService.validateAndGetProject(dtoProjectId);
						audit.setProject(project);
					}
				}
			}).register();
		
		defaultMapperFactory.classMap(Audit.class, AuditDto.class).field("interface1.interface_id", "interface_id").field("srcSystem.system_id", "source_system_id").field("tgtSystem.system_id", "target_system_id").field("project.project_id", "project_id").byDefault().register();

		//Audit-data
		defaultMapperFactory.classMap(CreateAuditDataDto.class, AuditData.class)
			.byDefault()
			.customize(new CustomMapper<CreateAuditDataDto, AuditData>() {
				@Override
				public void mapAtoB(CreateAuditDataDto createAuditDataDto, AuditData auditData, MappingContext context){
					super.mapAtoB(createAuditDataDto, auditData, context);
					Audit audit = auditService.validateAndGetAudit(createAuditDataDto.getAudit_id());
					auditData.setAudit(audit);
				}
			}).register();
			
		defaultMapperFactory.classMap(UpdateAuditDataDto.class, AuditData.class)
			.mapNulls(false)
			.byDefault()
			.customize(new CustomMapper<UpdateAuditDataDto, AuditData>(){
				@Override
				public void mapAtoB(UpdateAuditDataDto updateAuditDataDto, AuditData auditData, MappingContext context){
					super.mapAtoB(updateAuditDataDto, auditData, context);
					Long dtoAuditId = updateAuditDataDto.getAudit_id();
					if(dtoAuditId != null){
						Audit audit = auditService.validateAndGetAudit(dtoAuditId);
						auditData.setAudit(audit);
					}
				}
			}).register();

		defaultMapperFactory.classMap(AuditData.class, AuditDataDto.class).field("audit.audit_id", "audit_id").byDefault().register();

		return defaultMapperFactory;
	}
	
	@Bean
	MapperFacade mapperFacade() {
		return mapperFactory().getMapperFacade();
	}
}
