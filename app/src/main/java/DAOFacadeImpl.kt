//package com.example.linguaflow.repository.languageRepository
//
//import LanguageDatabaseConnection.dbQuery
//import com.example.linguaflow.repository.languageRepository.table.CommonData
//import com.example.linguaflow.repository.languageRepository.table.CommonDataLast
//import org.koin.core.annotation.Single
//
//@Single
//class DAOFacadeImpl: DAOFacade {
//    override suspend fun getCommonData(): CommonData = dbQuery {
//        CommonDataLast.fetchArticleInfoLastRecord()!!
//        }
//}