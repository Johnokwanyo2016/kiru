package com.example.myapple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.myapple.ui.theme.MyAppleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppleTheme {
                AppNavigation() // Call the navigation function
            }
        }
    }
}

@Composable
fun AppNavigation() {
    // Set up navigation with a NavHost
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(navController)
        }
        composable("registration") {
            RegistrationPage(navController)
        }
        composable("loginPage") { LoginPage(navController) }
        composable("registrationPage") { RegistrationPage(navController) }
        composable("forgotPasswordPage") { ForgotPasswordPage(navController) }
    }
}

@Composable
fun ForgotPasswordPage(navController: NavHostController) {
    // State variable for the email input
    var email by remember { mutableStateOf("") }

    // Layout for the forgot password page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Page title
        Text(
            text = "Forgot Password",
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Email input field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit button
        Button(
            onClick = {
                // Handle submit button click
                // Implement your password reset request here (e.g., make a network request)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}


@Composable
fun LoginPage(navController: NavController) {
    // State variables for username and password
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Layout for the login page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the logo
        val logo: Painter = painterResource(id = R.drawable.kangengalogo) // Replace with your actual logo resource
        Image(
            painter = logo,
            contentDescription = "Kangenga W Group Logo",
            modifier = Modifier.size(120.dp)
        )

        // Display the text message "Kangenga W Group"
        Text(
            text = "Kangenga W Group",
            fontSize = 24.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Username text field
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Move focus to the password text field */ }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password text field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login button
        Button(
            onClick = { /* Handle login button click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Forgot Password?" clickable text
        ClickableText(
            text = AnnotatedString("Forgot Password?"),
            onClick = { navController.navigate("forgotPasswordPage") }, // Navigate to the "Forgot Password" page
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // "Do you have an account? Signup" clickable text
        ClickableText(
            text = AnnotatedString("Do you have an account? Signup"),
            onClick = { navController.navigate("registration") }, // Navigate to the registration page
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun RegistrationPage(navController: NavHostController) {
    // State variables for form fields
    var firstName by remember { mutableStateOf("") }
    var secondName by remember { mutableStateOf("") }
    var nationalID by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var termsChecked by remember { mutableStateOf(false) } // State for checkbox

    // Use LazyColumn for efficient scrolling
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the registration page title
        item {
            Text(
                text = "Registration Page",
                fontSize = 24.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Add the image below the text "Registration Page"
            Image(
                painter = painterResource(id = R.drawable.kangengalogo), // Replace `your_image` with your actual image resource ID
                contentDescription = "Registration Page Image",
                modifier = Modifier
                    .size(200.dp) // Adjust the size as needed
                    .padding(bottom = 16.dp)
            )
        }

        // First name and last name fields
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                OutlinedTextField(
                    value = secondName,
                    onValueChange = { secondName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
            }
        }

        // National ID field
        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nationalID,
                onValueChange = { nationalID = it },
                label = { Text("National ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Email field
        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Phone number field
        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = phoneNo,
                onValueChange = { phoneNo = it },
                label = { Text("Phone No") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Password and confirm password fields
        item {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Checkbox for agreeing to terms and policy
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = termsChecked,
                    onCheckedChange = { termsChecked = it },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "I agree to the Terms and Policy")
            }
        }

        // Sign up button
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Handle sign-up button click
                    // Navigate to the login page
                    navController.navigate("loginPageRoute")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPagePreview() {
    MyAppleTheme {
        RegistrationPage(rememberNavController())
    }
}
