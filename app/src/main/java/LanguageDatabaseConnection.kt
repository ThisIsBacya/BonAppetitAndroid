//import com.example.linguaflow.repository.languageRepository.table.CommonDataLast
//import kotlinx.coroutines.Dispatchers
//import org.jetbrains.exposed.sql.Database
//import org.jetbrains.exposed.sql.SchemaUtils
//import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
//import org.jetbrains.exposed.sql.transactions.transaction
//
//object LanguageDatabaseConnection {
//    val url = "jdbc:postgresql://db.pcsrohdpguhrhoyynvcu.supabase.co:5432/postgres"
//    val user = "postgres"
//    val password = "linguaflowtestpass"
//    fun init() {
//        try {
//            val driver = "org.postgresql.Driver"
//            val database = Database.connect(
//                url = url,
//                driver = driver,
//                user = user,
//                password = password
//            )
//            transaction(database) {
//                SchemaUtils.create(CommonDataLast)
//            }
//        }catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    suspend fun <T> dbQuery(block: () -> T): T =
//        newSuspendedTransaction(Dispatchers.IO) { block() }
//}
