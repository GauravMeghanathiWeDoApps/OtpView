# Custom OTP View for Android Kotlin
A customizable OTP input view for Android, allowing developers to design an aesthetically pleasing and functional OTP input field.
Attributes

Below is a detailed list of attributes supported by the custom OTP view.

## Table of contents
- [Installation](#installation).
- [Usage](#usage).
- [Author](#author).
- [License](#license).

1. Box Configuration

  | Attribute    |  Format    | Description | Example |
  | :---:   | :---: | :---: | :---:|
  | app:boxCount | integer    | Number of OTP boxes.   | 6 |
  | app:boxSize  | dimension  | Size of each box (width and height).  | 48dp |
  | app:boxSpacing  | dimension  | Space between each box.  | 8dp |
  | app:boxElevation  | dimension  | Elevation or shadow depth for boxes.  | 4dp |
  | app:boxStrokeSize  | dimension  | Stroke width of the box border.  | 2dp |


2. Colors
   
  | Attribute    |  Format    | Description | Example |
  | :---:   | :---: | :---: | :---:|
  | app:activeBackgroundColor | color    | Background color of the active box.   | #FFFFFF (white)|
  | app:inactiveBackgroundColor  | color  | Background color of the inactive box.  | #E0E0E0 (light gray) |
  | app:focusStrokeColor  | color  | Border color when the box is focused. | #03A9F4 (light blue) |
  | app:activeStrokeColor  | color  | Border color of the active box. | #2196F3 (blue) |
  | app:inactiveStrokeColor  | color  | Border color when inactive.  | #BDBDBD (gray) |

3. Appearance

  | Attribute    |  Format    | Description | Example |
  | :---:   | :---: | :---: | :---:|
  | app:cursorDrawable="@drawable/custom_cursor" | reference    | Reference to a drawable used for cursor. | @drawable/cursor |
  | app:boxShape  | enum  | Shape of the OTP boxes.  | square, rounded, circle, line |
 
4. Corner Radius

  | Attribute    |  Format    | Description | Example |
  | :---:   | :---: | :---: | :---:|
  | app:boxCornerRadiusAll | dimension    | Uniform corner radius for all corners (if user will use this property for cornerRadius bellow cornerRadius property over ride with it). | 8dp |
  | app:boxCornerRadiusTopLeft  | dimension  | dimension  Corner radius for the top-left corner.  | 6dp |
  | app:boxCornerRadiusTopRight  | dimension  | dimension Corner radius for the top-Right corner.  | 6dp |
  | app:boxCornerRadiusBottomLeft  | dimension  | dimension Corner radius for the bottom-left corner.  | 6dp |
  | app:boxCornerRadiusBottomRight  | dimension  | dimension Corner radius for the bottm-right corner.  | 6dp |

5. Input Type

  | Attribute    |  Format  | Description | Example |
  | :---:   | :---: | :---: | :---:|
  | app:inputType |  flags  | Type of content allowed in the text boxes. Supported values all as Default editeText | text, number, etc.. |
 
        
  
## Installation
Instructions how to install and setup the Project to get a this Libaray into your Kotlin Project:

Step 1. Replace your Settings.gradles build file to this code
##
    dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
                    google()
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}

 for Kts file of Settings.gradle.kts use this code
 ##
     dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
                    google()
			mavenCentral()
			maven { url = uri("https://jitpack.io") }
		}
	}


Step 2. Add your build.gradles(Module:app) build file to this code
##
    dependencies {
          	        implementation 'com.github.GauravMeghanathiWeDoApps:OtpView:1.0.1'
      }

for Kts file of build.gradles.kts(Module:app) use this code without libs.versions.toml
##
    dependencies {
          	        implementation("com.github.GauravMeghanathiWeDoApps:OtpView:1.0.1")
      }

for Kts file of build.gradles.kts(Module:app) use this code with libs.versions.toml
##
    dependencies {
          	        implementation(libs.gauravmeghanathiwedoapps.otpview)
      }


for libs.versions.toml use this code
##
    [versions]
    otpviewVersion = "1.0.1"

    [libraries]
    gauravmeghanathiwedoapps-otpview = { module = "com.github.GauravMeghanathiWeDoApps:OtpView", version.ref = "otpviewVersion" }


## Usage

Add the custom view to your XML layout:

1. For Square Otp View Example

 ##
     <com.mg.otpview.OTPView
      android:id="@+id/otpViewSquare"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginTop="50dp"
      android:gravity="center"
      app:activeBackgroundColor="#FF6200EE"
      app:activeStrokeColor="#F0F00A"
      app:activeTextColor="@color/white"
      app:boxCount="6"
      app:boxElevation="6dp"
      app:boxShape="square"
      app:boxStrokeSize="0.8dp"
      app:boxCornerRadiusAll="12dp"
      app:boxSize="50dp"
      app:boxSpacing="6dp"
      app:cursorDrawable="@drawable/custom_cursor"
      app:focusBackgroundColor="#FFEB3B"
      app:focusStrokeColor="#000000"
      app:focusTextColor="#101010"
      app:inactiveBackgroundColor="#FFE0E0E0"
      app:inactiveStrokeColor="#FFE0E0E0"
      app:inputType="number"
      app:layout_constraintTop_toTopOf="parent"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent"
      app:textColor="@color/black"
      tools:viewBindingIgnore="true" />

2. For Rounded Otp View Example
##
    
     <com.mg.otpview.OTPView
      android:id="@+id/otpViewRounded"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginTop="10dp"
      android:layout_marginBottom="10dp"
      android:gravity="center"
      app:activeBackgroundColor="#FF6200EE"
      app:activeStrokeColor="#F0F00A"
      app:activeTextColor="@color/white"
      app:boxCount="6"
      app:boxElevation="6dp"
      app:boxShape="rounded"
      app:boxStrokeSize="0.8dp"
      app:boxCornerRadiusAll="12dp"
      app:boxSize="50dp"
      app:boxSpacing="6dp"
      app:cursorDrawable="@drawable/custom_cursor"
      app:focusBackgroundColor="#FFEB3B"
      app:focusStrokeColor="#000000"
      app:focusTextColor="#101010"
      app:inactiveBackgroundColor="#FFE0E0E0"
      app:inactiveStrokeColor="#FFE0E0E0"
      app:inputType="number"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toBottomOf="@id/otpViewSquare"
      app:textColor="@color/black"
      tools:viewBindingIgnore="true" />


3. For Circle Otp View Example
##
       <com.mg.otpview.OTPView
        android:id="@+id/otpViewCircle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:layout_marginBottom="10dp"
        android:gravity="center"
        app:activeBackgroundColor="#FF6200EE"
        app:activeStrokeColor="#F0F00A"
        app:activeTextColor="@color/white"
        app:boxCount="6"
        app:boxElevation="6dp"
        app:boxShape="circle"
        app:boxStrokeSize="0.8dp"
        app:boxCornerRadiusAll="12dp"
        app:boxSize="50dp"
        app:boxSpacing="6dp"
        app:cursorDrawable="@drawable/custom_cursor"
        app:focusBackgroundColor="#FFEB3B"
        app:focusStrokeColor="#000000"
        app:focusTextColor="#101010"
        app:inactiveBackgroundColor="#FFE0E0E0"
        app:inactiveStrokeColor="#FFE0E0E0"
        app:inputType="number"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/otpViewRoundedCustom"
        app:textColor="@color/black"
        tools:viewBindingIgnore="true" />


4. For Line Otp View Example
##
       <com.mg.otpview.OTPView
        android:id="@+id/otpViewLine"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:layout_marginBottom="10dp"
        android:gravity="center"
        app:activeBackgroundColor="#FF6200EE"
        app:activeStrokeColor="#FFFFFF"
        app:activeTextColor="#FF6200EE"
        app:boxCount="6"
        app:boxElevation="3dp"
        app:boxShape="line"
        app:boxStrokeSize="0.8dp"
        app:boxCornerRadiusAll="12dp"
        app:boxSize="50dp"
        app:boxSpacing="6dp"
        app:cursorDrawable="@drawable/custom_cursor"
        app:focusBackgroundColor="#FFEB3B"
        app:focusStrokeColor="#000000"
        app:focusTextColor="#101010"
        app:inactiveBackgroundColor="#FFE0E0E0"
        app:inactiveStrokeColor="#FFE0E0E0"
        app:inputType="number"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/otpViewCircle"
        app:textColor="@color/black"
        tools:viewBindingIgnore="true" />

5. For Custom rounded Otp View Example
##
    <com.mg.otpview.OTPView
      android:id="@+id/otpViewRoundedCustom"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginTop="10dp"
      android:layout_marginBottom="10dp"
      android:gravity="center"
      app:activeBackgroundColor="#FF6200EE"
      app:activeStrokeColor="#F0F00A"
      app:activeTextColor="@color/white"
      app:boxCount="6"
      app:boxElevation="6dp"
      app:boxShape="rounded"
      app:boxStrokeSize="0.8dp"
      app:boxCornerRadiusTopLeft="12dp"
      app:boxSize="50dp"
      app:boxSpacing="6dp"
      app:cursorDrawable="@drawable/custom_cursor"
      app:focusBackgroundColor="#FFEB3B"
      app:focusStrokeColor="#000000"
      app:focusTextColor="#101010"
      app:inactiveBackgroundColor="#FFE0E0E0"
      app:inactiveStrokeColor="#FFE0E0E0"
      app:inputType="number"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toBottomOf="@id/otpViewRounded"
      app:textColor="@color/black"
      tools:viewBindingIgnore="true" />


# Next in your code assign otpView to a variable like your .kts class file
val otpView = findViewById(R.id.otpView)

implement OnOtpCompletionListener

# otpView.setOTP("123456")

##
	otpView.setOnCompleteOtpListener{ otp->
        	txtOtpView.text = buildString {
                append("OTP is - ")
                append(otp)
            	}
    	}

# otpView.length()
  Return the otp box length how Many otp box fill it.

# otpView.isOTPFilled  
Return if The all otp box fill then true other wise it return false. 

# otpView.getOtp()
Return otp box value in a String

# otpView.clearOTP()
Clear otp Box if it is fill.

![image](https://github.com/user-attachments/assets/20cb0059-0191-48bc-8da9-23b44c5747a0)
![image](https://github.com/user-attachments/assets/ffa74510-f8f9-4f31-8e63-1d725a8a97fa)
![image](https://github.com/user-attachments/assets/3f97f616-b99c-495a-8d1d-23e0c9cf9a8b)
![image](https://github.com/user-attachments/assets/3fa97335-e471-434e-a766-f8a29be0d393)
![image](https://github.com/user-attachments/assets/01698752-bcfa-4959-8206-16fc6612c9cd)
![image](https://github.com/user-attachments/assets/30a5619c-89b0-4f93-8db6-14e4267d075d)


## Author
GAURAV MEGHANATHI

## License

MIT License

Copyright (c) 2024 Gaurav Meghanathi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
