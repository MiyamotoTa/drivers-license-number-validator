package takahiro.miyamoto.dlnv.application.impl

import org.springframework.stereotype.Service
import takahiro.miyamoto.dlnv.application.LicenseNumberValidationService
import takahiro.miyamoto.dlnv.domain.model.license.LicenseNumber
import takahiro.miyamoto.dlnv.infrastructure.persistence.PublicSafetyCommissionMapper

/**
 * @author miyamoto_ta
 */
@Service
class LicenseNumberValidationServiceImpl(
        private val publicSafetyCommissionMapper: PublicSafetyCommissionMapper
) : LicenseNumberValidationService {
    override fun validate(publicSafetyCommissionId: Int,
                          licenseAcquisitionYear: Int,
                          controlNumber: Int,
                          checkDigit: Int,
                          reissuesNumber: Int): Boolean {
        val publicSafetyCommission = publicSafetyCommissionMapper.findById(publicSafetyCommissionId)

        val license = LicenseNumber(publicSafetyCommission, licenseAcquisitionYear, controlNumber, checkDigit, reissuesNumber)

        return license.validatePublicSafetyCommission() && license.validateLicenseAcquisitionYear() && license.validateControlNumber() && license.validateCheckDigit() && license.validateReissuesNumber()
    }

}