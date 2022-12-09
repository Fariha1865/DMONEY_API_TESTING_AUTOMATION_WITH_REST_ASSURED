package testrunner;

import controller.User;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utils.Utils;

import java.io.IOException;

public class UserTestRunner {



    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public static class JUnitOrderedTests {

         String username;
         String password;
         String Phone_number;
         String email;



        @Test
        public void A_doLogin() throws javax.naming.ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            user.callingLoginAPI("salman@grr.la", "1234");
            String messageExpected = "Login successfully";
            System.out.println(messageExpected);
            Assert.assertEquals(user.getMessage(), messageExpected);
        }

        @Test
        public void B_getUserList() throws IOException {
            User user = new User();
            String msg = user.callingUserListAPI();
            System.out.println(msg);
            Assert.assertEquals(msg,"User list");
        }

        @Test
        public void C_CreateAgent() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            Utils utils = new Utils();
            String rand = String.valueOf(Utils.generateRandomNumber(10000, 9999));
            utils.generateRandomData();
            String Phone = "077320" + rand;
            username = utils.getName();
            password = utils.getPassword();
            Phone_number=Utils.phone;
            //System.out.println(Phone_number);
            //System.out.println(username);
            String msg = user.CreatingUser(username, utils.getEmail(), password, Phone_number, "new123", "Agent");

            System.out.println("Agent created successfully");
            Assert.assertEquals(msg, "User created successfully");
        }
        @Test
        public void D_Get_Agent_Number() throws javax.naming.ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg= user.Get_User_Number();
            Utils.Agent_Number=msg;
            //System.out.println(Utils.Agent_Number);
            //System.out.println(msg);

        }

        @Test
        public void E_CreateCustomer() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            Utils utils = new Utils();
            String rand = String.valueOf(Utils.generateRandomNumber(10000, 9999));
            utils.generateRandomData();
            Phone_number=Utils.phone;
            //System.out.println(Phone_number);
            username = utils.getName();
            password = utils.getPassword();
            email = utils.getEmail();
            //System.out.println(username);
            String msg = user.CreatingUser(username, email, password, Phone_number, "new123", "Customer");

            System.out.println("Customer created successfully");
            Assert.assertEquals(msg, "User created successfully");
        }


        @Test
        public void F_doCustomer_Login() throws javax.naming.ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            user.callingLoginAPI(Utils.email,Utils.password);
            String messageExpected = "Login successfully";
            System.out.println("Customer Logged In Successfully");
            Assert.assertEquals(user.getMessage(), messageExpected);
        }

        @Test
        public void G_Get_Customer_Id() throws javax.naming.ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg= user.Get_Customer0Id();
            Utils.id=msg;
            //System.out.println(msg);

        }

        @Test
        public void H_Update_Customer() throws javax.naming.ConfigurationException, IOException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            Utils utils = new Utils();
            String rand = String.valueOf(Utils.generateRandomNumber(10000, 9999));
            String msg= user.UpdateCustomer("01843"+rand);
            String messageExpected = "User updated successfully";
            System.out.println("Customer Phone Number Updated successfully");
            Assert.assertEquals(msg, messageExpected);

        }
        @Test
        public void I_Deposit_To_Agent() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg = user.DepositToAgent("2000");

            System.out.println("Deposit To Agent successful");
            Assert.assertEquals(msg, "Deposit successful");
        }
        @Test
        public void J_Check_Agent_Balance() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg=user.CheckAgentBalance();
            //System.out.println(Utils.Agent_Number);
            System.out.println("Agent Balance After Deposit: "+msg);

        }

        @Test
        public void K_Deposit_To_Customer() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg = user.DepositToCustomer("1000");

            System.out.println("Deposit To Customer successful");
            Assert.assertEquals(msg, "Deposit successful");
        }

        @Test
        public void L_Check_Customer_Balance() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg=user.CheckCustomerBalance();
            //System.out.println(Utils.Customer_Number);
            System.out.println("Customer Balance After Deposit: "+msg);

        }

        @Test
        public void M_Check_Agent_Balance() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg=user.CheckAgentBalance();
            //System.out.println(Utils.Agent_Number);
            System.out.println("Agent Balance After Deposit To Customer: "+msg);

        }
        @Test
        public void N_WithdrawByCustomer() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg = user.CashoutByCustomer("50");

            System.out.println("Withdraw successful By Customer");
            Assert.assertEquals(msg, "Withdraw successful");
        }

        @Test
        public void O_Check_Customer_Balance() throws IOException, javax.naming.ConfigurationException, org.apache.commons.configuration.ConfigurationException {
            User user = new User();
            String msg=user.CheckCustomerBalance();
            //System.out.println(Utils.Customer_Number);
            System.out.println("Customer Balance After Withdraw: "+msg);

        }


    }
}
