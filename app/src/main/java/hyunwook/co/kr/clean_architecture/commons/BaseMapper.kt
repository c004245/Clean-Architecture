package hyunwook.co.kr.clean_architecture.commons

interface BaseMapper<in A, out B> {
    fun map(type: A?): B
}