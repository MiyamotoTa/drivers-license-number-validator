package takahiro.miyamoto.dlnv.interfaces.validating.web

import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import takahiro.miyamoto.dlnv.interfaces.validating.facade.LicenseNumberValidationServiceFacade

/**
 * @author miyamoto_ta
 */
@RestController
@RequestMapping("/license")
class LicenseController(
        private val licenseNumberValidationServiceFacade: LicenseNumberValidationServiceFacade
) {
    @GetMapping("/validation")
    fun validation(mav: ModelAndView): ModelAndView {
        mav.viewName = "validation"
        return mav
    }

    @PostMapping("/validation")
    fun licenseValidation(@RequestParam("license") licenseNumber: String, mav: ModelAndView): ModelAndView {
        val result = if (licenseNumberValidationServiceFacade.validateLicense(licenseNumber)) {
            "運転免許証番号${licenseNumber}は正しいです。"
        } else {
            "運転免許証番号${licenseNumber}は不正です。"
        }

        mav.addObject("result", result)
        mav.viewName = "validation"
        return mav
    }
}