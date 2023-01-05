import org.example.extendEnum.MemberTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MemberTypeEnumTest {

    private static Map<MemberTypeEnum, Supplier<String>> enumMap;

    private static final String BRONZE_DISCOUNT_POLICY = "BRONZE MEMBER DISCOUNT POLICY";
    private static final String SILVER_DISCOUNT_POLICY = "SILVER MEMBER DISCOUNT POLICY";
    private static final String GOLD_DISCOUNT_POLICY = "GOLD MEMBER DISCOUNT POLICY";

    @BeforeEach
    void setUp() {
        enumMap = new EnumMap<>(MemberTypeEnum.class);
        enumMap.put(MemberTypeEnum.BRONZE, () -> BRONZE_DISCOUNT_POLICY);
        enumMap.put(MemberTypeEnum.SILVER, () -> SILVER_DISCOUNT_POLICY);
        enumMap.put(MemberTypeEnum.GOLD, () -> GOLD_DISCOUNT_POLICY);
    }

    @ParameterizedTest
    @MethodSource("provideDiscountPolicys")
    void testMemberTypeEnumMap(final MemberTypeEnum memberTypeEnum, final String discountPolicy) {
        Assertions.assertEquals(discountPolicy, enumMap.get(memberTypeEnum).get());
    }

    private static Stream<Arguments> provideDiscountPolicys() {
        return Stream.of(
                Arguments.of(MemberTypeEnum.BRONZE, BRONZE_DISCOUNT_POLICY),
                Arguments.of(MemberTypeEnum.SILVER, SILVER_DISCOUNT_POLICY),
                Arguments.of(MemberTypeEnum.GOLD, GOLD_DISCOUNT_POLICY)
        );
    }
}
