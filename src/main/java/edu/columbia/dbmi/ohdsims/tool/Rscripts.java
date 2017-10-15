package edu.columbia.dbmi.ohdsims.tool;

public class Rscripts {

// age distribution
//	library(CohortMethod) 
//	library(SqlRender)
//
//	## create a connection
//	connectionDetails <- createConnectionDetails(dbms = "postgresql",
//	                                             server = "localhost/synpuf1k",
//	                                             user = "postgres",
//	                                             password = "granite")
//
//	cdmDatabaseSchema <- "omopcdm"
//
//	sql <- paste(" 
//					SELECT p.person_id, 2017-year_of_birth AS AGE
//					FROM person AS p INNER JOIN (
//						SELECT person_id FROM condition_occurrence AS a
//							INNER JOIN concept AS b ON a.condition_concept_id=b.concept_id AND b.concept_name LIKE 'Alzheimer''s disease'
//					) AS a ON p.person_id=a.person_id	
//	             ")
//
//	sql <- SqlRender::renderSql(sql, cdm_database_schema = cdmDatabaseSchema)$sql
//	sql <- SqlRender::translateSql(sql, targetDialect = connectionDetails$dbms)$sql
//	connection <- connect(connectionDetails)
//	data <- querySql(connection, sql)
//
//	## analyze data, get age distribution by bins
//	age.num <- data$AGE
//	age.group <- cut(age.num,breaks = c(seq(0,100,by=10)))
//	age.group.df <- as.data.frame(table(age.group))

// top drugs
//	library(CohortMethod) 
//	library(SqlRender)
//
//	## create a connection
//	connectionDetails <- createConnectionDetails(dbms = "postgresql",
//	                                             server = "localhost/synpuf1k",
//	                                             user = "postgres",
//	                                             password = "granite")
//
//	cdmDatabaseSchema <- "omopcdm"
//
//	sql <- paste(" 
//				SELECT * FROM (SELECT con.concept_id, con.concept_name, COUNT(con.concept_id) AS drug_frequency FROM (
//					SELECT * FROM (
//						SELECT person_id  FROM condition_occurrence 
//							WHERE condition_concept_id=316866) AS a 
//								INNER JOIN drug_exposure AS de ON a.person_id = de.person_id) b
//								INNER JOIN concept AS con ON b.drug_concept_id = con.concept_id GROUP BY con.concept_id) result ORDER BY result.drug_frequency DESC
//
//	             ")
//
//	sql <- SqlRender::renderSql(sql, cdm_database_schema = cdmDatabaseSchema)$sql
//	sql <- SqlRender::translateSql(sql, targetDialect = connectionDetails$dbms)$sql
//	connection <- connect(connectionDetails)
//	data <- querySql(connection, sql)
//
//	## analyze data, get age distribution by bins
//	rankCutoff <- 10;
//	data[c(1:rankCutoff),]

// gender ratio
//	library(CohortMethod) 
//	library(SqlRender)
//
//	## create a connection
//	connectionDetails <- createConnectionDetails(dbms = "postgresql",
//	                                             server = "localhost/synpuf1k",
//	                                             user = "postgres",
//	                                             password = "granite")
//
//	cdmDatabaseSchema <- "omopcdm"
//
//	sql <- paste(" 
//				SELECT c.concept_name,d.gender_freq FROM concept AS c
//					INNER JOIN (
//							SELECT gender_concept_id, COUNT(gender_concept_id) AS gender_freq FROM person AS p 
//							INNER JOIN (
//									SELECT person_id FROM condition_occurrence AS a 
//										INNER JOIN (
//											SELECT concept_id FROM concept 
//											WHERE concept_name LIKE 'Hypertensive disorder'
//										) AS b ON a.condition_concept_id=b.concept_id
//								) AS c ON p.person_id=c.person_id
//							GROUP BY gender_concept_id
//						) AS d ON c.concept_id=d.gender_concept_id
//	             ")
//
//	sql <- SqlRender::renderSql(sql, cdm_database_schema = cdmDatabaseSchema)$sql
//	sql <- SqlRender::translateSql(sql, targetDialect = connectionDetails$dbms)$sql
//	connection <- connect(connectionDetails)
//	data <- querySql(connection, sql)
//
//	## analyze data, get age distribution by bins
//	femaleNum <- data[1,2]
//	maleNum <- data[2,2]
//	ratio <- maleNum/femaleNum



	
	
}
