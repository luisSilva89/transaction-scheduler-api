package com.luisilva.transactionschedulerapp.modules;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDateException;
import com.luisilva.transactionschedulerapp.transactionFee.FeeStrategyA;
import com.luisilva.transactionschedulerapp.transactionFee.FeeStrategyB;
import com.luisilva.transactionschedulerapp.transactionFee.FeeStrategyC;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TransactionFeeModuleTests {


    @Test
    public void feeStrategyAShouldReturnAFeeWhenValuesPassedAreWithinBounds() {
        // Given
        double amount = 500.0;
        LocalDate schedulingDate = LocalDate.now();

        FeeStrategyA feeStrategyA = new FeeStrategyA();

        // When
        double fee = feeStrategyA.calculateFee(amount, schedulingDate);

        // Then
        Assert.assertEquals(18.0, fee, 0.01);
    }

    @Test
    public void feeStrategyAShouldThrowAnExceptionWhenDateIsOutOfBounds() {
        // Given
        double amount = 500.0;
        LocalDate schedulingDate = LocalDate.now().plusDays(1);

        FeeStrategyA feeStrategyA = new FeeStrategyA();

        // When/Then
        try {
            double fee = feeStrategyA.calculateFee(amount, schedulingDate);
            // If the calculation succeeds, fail the test
            fail("Expected InvalidSchedulingDateException to be thrown");
        } catch (InvalidSchedulingDateException e) {
            // The exception is thrown as expected
            assertNotNull(e);
        }
    }

    @Test
    public void feeStrategyBShouldReturnAFeeWhenValuesPassedAreWithinBounds() {
        // Given
        double amount = 1500.0;
        LocalDate schedulingDate = LocalDate.now().plusDays(10);

        FeeStrategyB feeStrategyB = new FeeStrategyB();

        // When
        double fee = feeStrategyB.calculateFee(amount, schedulingDate);

        // Then
        Assert.assertEquals(135.0, fee, 0.01);
    }

    @Test
    public void feeStrategyBShouldThrowAnExceptionWhenDateIsOutOfBounds() {
        // Given
        double amount = 1500.0;
        LocalDate schedulingDate = LocalDate.now().plusDays(11);

        FeeStrategyB feeStrategyB = new FeeStrategyB();

        // When/Then
        try {
            double fee = feeStrategyB.calculateFee(amount, schedulingDate);
            // If the calculation succeeds, fail the test
            fail("Expected InvalidSchedulingDateException to be thrown");
        } catch (InvalidSchedulingDateException e) {
            // The exception is thrown as expected
            assertNotNull(e);
        }
    }

    @Test
    public void feeStrategyCShouldReturnAFeeWhenValuesPassedAreWithinBounds() {
        // Given
        FeeStrategyC feeStrategyC = new FeeStrategyC();

        /**
         * Test scenario where tax applied is TAX1
         */
        double amount1 = 2500.0;
        LocalDate schedulingDate1 = LocalDate.now().plusDays(15);

        // When
        double fee1 = feeStrategyC.calculateFee(amount1, schedulingDate1);

        // Then
        Assert.assertEquals(205, fee1, 0.01);

        /**
         * Test scenario where tax applied is TAX2
         */
        double amount2 = 2500.0;
        LocalDate schedulingDate2 = LocalDate.now().plusDays(25);

        // When
        double fee2 = feeStrategyC.calculateFee(amount2, schedulingDate2);

        // Then
        Assert.assertEquals(172.5, fee2, 0.01);


        /**
         * Test scenario where tax applied is TAX3
         */
        double amount3 = 2500.0;
        LocalDate schedulingDate3 = LocalDate.now().plusDays(35);

        // When
        double fee3 = feeStrategyC.calculateFee(amount3, schedulingDate3);

        // Then
        Assert.assertEquals(117.5, fee3, 0.01);

        /**
         * Test scenario where tax applied is TAX4
         */
        double amount4 = 2500.0;
        LocalDate schedulingDate4 = LocalDate.now().plusDays(45);

        // When
        double fee4 = feeStrategyC.calculateFee(amount4, schedulingDate4);

        // Then
        Assert.assertEquals(42.5, fee4, 0.01);
    }

    @Test
    public void feeStrategyCShouldThrowAnExceptionWhenDateIsOutOfBounds() {
        // Given
        double amount = 2500.0;
        LocalDate schedulingDate = LocalDate.now().plusDays(5);

        FeeStrategyC feeStrategyC = new FeeStrategyC();

        // When/Then
        try {
            double fee = feeStrategyC.calculateFee(amount, schedulingDate);
            // If the calculation succeeds, fail the test
            fail("Expected InvalidSchedulingDateException to be thrown");
        } catch (InvalidSchedulingDateException e) {
            // Verify that the exception is thrown as expected
            assertNotNull(e);
        }
    }

    @Test
    public void testTransactionFeeCalculator() {

    }

}
