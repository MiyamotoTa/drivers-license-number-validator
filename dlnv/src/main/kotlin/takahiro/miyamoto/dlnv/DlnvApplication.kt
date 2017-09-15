package takahiro.miyamoto.dlnv

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import takahiro.miyamoto.dlnv.infrastructure.persistence.PublicSafetyCommissionMapper

@SpringBootApplication
@RestController
class DlnvApplication(
        val publicSafetyCommisionMapper: PublicSafetyCommissionMapper
) {
    @RequestMapping("/")
    fun hello() = publicSafetyCommisionMapper.findById(40)
}

fun main(args: Array<String>) {
    SpringApplication.run(DlnvApplication::class.java, *args)
}
