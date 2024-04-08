package mx.com.blackengine.entities.tables

import mx.com.blackengine.entities.columns.*
import org.jetbrains.exposed.sql.Table

object Formulations : InsertedUpdatedDeletedTable("formulation") {
    val company = reference("company", Companies)
    val name = citext("name")
    val minimumBatchSize = double("minimum_batch_size").check { it greater 0.0 }
    val calculationBaseValue = double("calculation_base")
    val calculationBaseUnit = reference("calculation_base_unit", EnumUnits)
    val componentsPercentageNature = reference("percentage_nature", EnumPercentageNatures)
    val outputProduct = reference("output_product", Products)
}

object FormulationRawMaterials : Table("formulation_raw_materials") {
    val formulation = reference("formulation", Formulations)
    val product = reference("product", Products)
    val fraction = double("fraction").check { it.between(0.0, 1.0) }

    init {
        uniqueIndex(columns = arrayOf(formulation, product))
    }
}

object QualityMetricsCatalog : InsertedUpdatedDeletedTable("quality_metrics_catalog") {
    val formulation = reference("formulation", Formulations)
    val name = citext("name")
    val unit = reference("unit", EnumUnits)
}

object FormulationQualityMetrics : Table("formulation_quality_metrics") {
    val formulation = reference("formulation", Formulations)
    val qualityMetric = reference("quality_metric", QualityMetricsCatalog)
    val idealRange = numericRange("ideal_range")
}

object ProductionBatches : InsertedUpdatedDeletedTable("production_batches") {
    val currentStage = reference("current_stage", Stages)
    val company = reference("company", Companies)
    val formulation = reference("formulation", Formulations)
    val neededOutput = double("needed_output").check { it greater 0.0 } // given in the same units as the formulation
    val deadline = timestampWithTimeZone("deadline")
    val creator = reference("creator", Users)
    val salesOrder = reference("order", Orders).nullable()
    val responsible = reference("responsible", Users).nullable()
    val test = reference("test", FormulationQualityTests).nullable()
}

object FormulationQualityTests : TimestampTable("formulation_quality_tests") {
    val currentStage = reference("current_stage", Stages)
    val productionBatch = reference("production_batch", ProductionBatches)
    val responsible = reference("responsible", Users).nullable()
}

object FormulationQualityTestMetrics : TimestampTable("formulation_quality_test_metrics") {
    val test = reference("formulation_quality_test", FormulationQualityTests)
    val qualityMetric = reference("quality_metric", QualityMetricsCatalog)
    val value = double("value")
}