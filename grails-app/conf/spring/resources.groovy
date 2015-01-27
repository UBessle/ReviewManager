// Place your Spring DSL code here
import org.bessle.rm.CustomMarshallerRegistrar
import org.bessle.rm.ReviewExcelImporter

beans = {
    customMarshallerRegistrar(CustomMarshallerRegistrar)
    reviewExcelImporter(ReviewExcelImporter) {
        excelImportService = ref("excelImportService")
    }
}
