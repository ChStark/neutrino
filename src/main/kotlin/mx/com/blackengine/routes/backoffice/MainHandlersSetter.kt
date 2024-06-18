package mx.com.blackengine.routes.backoffice

import io.vertx.ext.web.Router
import io.vertx.ext.web.templ.pebble.PebbleTemplateEngine
import mx.com.blackengine.html.backoffice.regularHtmlResult
import org.jetbrains.exposed.sql.Database

fun mainHandlersSetter(router: Router, db: Database, templateEngine: PebbleTemplateEngine) {
    router.get("/").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/shortcuts.html.peb", regularHtmlResult(ctx))
    }

    router.get("/panel").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/panel.html.peb", regularHtmlResult(ctx))
    }

    router.get("/orders").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/orders.html.peb", regularHtmlResult(ctx))
    }

    router.get("/payments").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/payments.html.peb", regularHtmlResult(ctx))
    }

    router.get("/products").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/products.html.peb", regularHtmlResult(ctx))
    }

    router.get("/government-digital-tax-receipts").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/government-digital-tax-receipts.html.peb", regularHtmlResult(ctx))
    }

    router.get("/shipments").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/shipments.html.peb", regularHtmlResult(ctx))
    }

    router.get("/routes").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/routes.html.peb", regularHtmlResult(ctx))
    }

    router.get("/cash-registers").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/cash-registers.html.peb", regularHtmlResult(ctx))
    }

    router.get("/anomalies").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/anomalies.html.peb", regularHtmlResult(ctx))
    }

    router.get("/bi").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/bi.html.peb", regularHtmlResult(ctx))
    }

    router.get("/workflows").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/workflows.html.peb", regularHtmlResult(ctx))
    }

    router.get("/admin").handler { ctx ->
        templateEngine.render(mapOf("foo" to "bar"), "templates/backoffice/admin.html.peb", regularHtmlResult(ctx))
    }
}