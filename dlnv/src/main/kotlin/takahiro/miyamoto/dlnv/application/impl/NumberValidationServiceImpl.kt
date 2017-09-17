package takahiro.miyamoto.dlnv.application.impl

import takahiro.miyamoto.dlnv.application.NumberValidationService
import takahiro.miyamoto.dlnv.domain.model.license.LicenseNumber
import takahiro.miyamoto.dlnv.infrastructure.persistence.PublicSafetyCommissionMapper

/**
 * @author miyamoto_ta
 */
class NumberValidationServiceImpl(
        private val publicSafetyCommissionMapper: PublicSafetyCommissionMapper
) : NumberValidationService {
    override fun validate(publicSafetyCommissionId: Int,
                          licenseAcquisitionYear: Int,
                          controlNumber: Int,
                          checkDigit: Int,
                          reissuesNumber: Int): Boolean {
        val publicSafetyCommission = publicSafetyCommissionMapper.findById(publicSafetyCommissionId)

        val license = LicenseNumber(publicSafetyCommission, licenseAcquisitionYear, controlNumber, checkDigit, reissuesNumber)

        return license.validateLicenseAcquisitionYear() && license.validateControlNumber() && license.validateCheckDigit() && license.validateReissuesNumber()
    }

}