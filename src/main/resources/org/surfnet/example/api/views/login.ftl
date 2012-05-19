<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login University Foo</title>
	<link rel="stylesheet" href="/assets/bootstrap-2.0.2/css/bootstrap.css" />
	<link rel="stylesheet" href="/assets/awesome-1.0.0/css/font-awesome.css" />
	<script type="text/javascript" src="/assets/js/jquery-1.7.2.js"></script>
	<script type="text/javascript"
		src="/assets/bootstrap-2.0.2/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span8">
				<form class="form-horizontal" id="registerHere" method="post"
					action="/authorize">
					<fieldset>

						<legend>Login with Student identifier and Password</legend>
						<p class="help-block">Hint: foo1 - foo10</p>
						
						<div class="control-group">
							<label class="control-label">Student ID</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="username"
									name="username" rel="popover"
									data-content="Enter your student ID."
									data-original-title="Student ID">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="password"
									name="password" rel="popover"
									data-content="What's your password?"
									data-original-title="Password">
							</div>
						</div>
					</fieldset>
					<input type="hidden" name="redirect_uri"
						value="${authorizationRequest.redirectUri}" /> 
					<input
						type="hidden" name="clientId"
						value="${authorizationRequest.clientId}" /> 
					<input type="hidden"
						name="state" value="${authorizationRequest.state}" />
					<input type="hidden"
						name="client_id" value="${authorizationRequest.clientId}" />
					
					<div class="control-group">
						<label class="control-label"></label>
						<div class="controls">
							<button type="submit" class="btn btn-success">Login</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>