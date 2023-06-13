//package com.example.linguaflow.repository.languageRepository.table
//
//import org.jetbrains.exposed.sql.ResultRow
//import org.jetbrains.exposed.sql.Table
//import org.jetbrains.exposed.sql.javatime.timestamp
//import org.jetbrains.exposed.sql.selectAll
//import org.jetbrains.exposed.sql.transactions.transaction
//
//data class CommonData(
//    val version: Float,
//    val maxProgress: Int
//)
//
//object CommonDataLast : Table("CommonData") {
//    val version = float("version")
//    val created = timestamp("created_at")
//    val maxProgress = integer("maxProgress")
//    override val primaryKey = PrimaryKey(version)
//
//    fun fetchArticleInfoLastRecord(): CommonData? = transaction {
//        CommonDataLast.selectAll().lastOrNull()?.toArticleInfo()
//    }
//
//    private fun ResultRow.toArticleInfo() = CommonData(
//        this[version],
//        this[maxProgress]
//    )
//}