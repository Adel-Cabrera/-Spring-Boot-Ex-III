package cl.angel.tdd;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestEmployeeDetails {
   EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
   EmployeeDetails employee = new EmployeeDetails();

   //test to check appraisal
   @Test
   public void testCalculateAppriasal() {
      employee.setName("Rajeev");
      employee.setAge(25);
      employee.setMonthlySalary(8000);
		
      double appraisal = empBusinessLogic.calculateAppraisal(employee);
      assertEquals(500, appraisal, 0.0);
   }

   //test to check appraisal
   @Test
   public void testCalculateAppriasalMayor30() {
      employee.setName("Pedro");
      employee.setAge(31);
      employee.setMonthlySalary(8000);
		
      double appraisal = empBusinessLogic.calculateAppraisal(employee);
      assertEquals(2000, appraisal, 0.0);
   }
   
   //test to check appraisal
   @Test
   public void testCalculateAppriasalEnviarCorreoPedro() {
      employee.setName("Pedro");
      employee.setAge(25);
      employee.setMonthlySalary(10000);
		
      double appraisal = empBusinessLogic.calculateAppraisal(employee);
      assertEquals(1000, appraisal, 0.0);
   }


     
   
   // test to check yearly salary
   @Test
   public void testCalculateYearlySalary() {
      employee.setName("Rajeev");
      employee.setAge(25);
      employee.setMonthlySalary(8000);
		
      double salary = empBusinessLogic.calculateYearlySalary(employee);
      assertEquals(96000, salary, 0.0);
   }
}