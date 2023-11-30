/**
 * 
 */

function validateForm(){
	const dob = document.getElementById("dob").value;
	const mobileNumber = document.getElementById("mobileNumber").value;
	const mobileError = document.getElementById("mobile-error");
	const dobError = document.getElementById("dob-error");
	const mobilePattern = /^[6-9]\d{9}$/;
	
	mobileError.textContent='';
	dobError.textContent='';
	if(!dob){
		dobError.textContent = 'Date of Birth is Required';
		return false;
	} else {
		const currentDate = new Date();
		const enteredDate = new Date(dob);
		const ageDifference = currentDate.getFullYear() - enteredDate.getFullYear();
		if(ageDifference < 18){
			dobError.textContent = 'Date of Birth must be 18+';
			return false;
		}
	}
	
	if(!mobileNumber){
		mobileError.textContent = 'Mobile Number is Required';
		return false;
	} else {
		if(!mobilePattern.test(mobileNumber)){
			mobileError.textContent = 'Please Enter Valid Mobile Number';
			return false;
		}
	}
	

	
	
}