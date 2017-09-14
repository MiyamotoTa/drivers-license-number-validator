package takahiro.miyamoto.dlnv.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import takahiro.miyamoto.dlnv.domain.PublicSafetyCommission

@Mapper
interface PublicSafetyCommissionMapper {

    @Select("""
SELECT
    id, name
FROM
    public_safety_commissions
WHERE
    id = #{id}
    """)
    fun findById(id:Int):PublicSafetyCommission
}