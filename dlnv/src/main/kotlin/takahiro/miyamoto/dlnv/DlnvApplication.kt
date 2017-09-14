package takahiro.miyamoto.dlnv

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class DlnvApplication {
    @RequestMapping("/")
    fun hello() = "hello world"
}

fun main(args: Array<String>) {
    SpringApplication.run(DlnvApplication::class.java, *args)
}
