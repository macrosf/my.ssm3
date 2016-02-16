<!-- page 203 -->
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div>
<h2>Create a free Spitter account</h2>
<sf:form method="POST" modelAttribute="spitter" 
	enctype="multipart/form-data">
	<fieldset>
	<table cellspacing="0">
		<tr>
			<th><label for="user_full_name">Full name:</label></th>
			<td><sf:input path="fullName" size="15" id="usr_full_name" /></td>
				<%-- <sf:errors path="fullName" delimiter="," cssClass="error"/> --%>
		</tr>
		<tr>
			<th></th>
			<td><sf:errors path="fullName" delimiter="," cssClass="error"/></td>	
		</tr>
		
		<tr>
			<th><label for="user_screen_name">Username:</label></th>
			<td><sf:input path="username" size="15" maxlength="15"
				id="user_screen_name" />
				<small id="username_msg">No spaces, please.</small>
				<%-- <sf:errors path="username" cssClass="error"/> --%>
			</td>
		</tr>
		<tr>
			<th></th>
			<td><sf:errors path="username" cssClass="error"/></td>	
		</tr>
				
		<tr>
			<th><label for="user_password">Password:</label></th>
			<td><sf:password path="password" size="30"
					showPassword="true" id="user_password"/>
				<small>6 characters or more (be tricky!)</small>
				<%-- <sf:errors path="password" cssClass="error"/> --%>
			</td>	
		</tr>	
		<tr>
			<th></th>
			<td><sf:errors path="password" cssClass="error"/></td>	
		</tr>

		<tr>
			<th><label for="user_mail">Email Address:</label></th>
			<td><sf:input path="email" size="30" id="user_mail"/>
				<small>In case you forget something</small>
				<%-- <sf:errors path="email" cssClass="error" /> --%>
			</td>
		</tr>
		<tr>
			<th></th>
			<td><sf:errors path="email" cssClass="error" /></td>	
		</tr>
		<tr>
			<th><label for="image">Profile image:</label></th>
			<td><input name="image" type="file"></td>
		</tr>
		<tr>
			<th></th>
			<td>
				<sf:checkbox path="updateByEmail"
					id="user_send_email_newsletter"/>
				<label for="user_send_email_newsletter">
					Send me email updates!</label>
			</td>		
		</tr>

		<tr>
			<th></th>
			<td><input name="commit" type="submit"
				value="I accept. Create my account." />
			</td>
		</tr>	
	</table>	
	</fieldset>
</sf:form>
</div>
<%-- 
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div>
<h2>Create a free Spitter account</h2>

<sf:form method="POST" modelAttribute="spitter" 
         enctype="multipart/form-data">         
   <fieldset> 
   <table cellspacing="0">
      <tr>
         <th><sf:label path="fullName">Full name:</sf:label></th>
         <td><sf:input path="fullName" size="15" /><br/>
             <sf:errors path="fullName" cssClass="error" />
         </td>
      </tr>
      <tr>
         <th><sf:label path="username">Username:</sf:label></th>
         <td><sf:input path="username" size="15" maxlength="15" />
              <small id="username_msg">No spaces, please.</small><br/>
             <sf:errors path="username" cssClass="error" />

            </td>
      </tr>
      <tr>
         <th><sf:label path="password">Password:</sf:label></th>
         <td><sf:password path="password" size="30" 
                            showPassword="true"/> 
             <small>6 characters or more (be tricky!)</small><br/>
             <sf:errors path="password" cssClass="error" />
            </td>
      </tr>

      <tr>
         <th><sf:label path="email">Email Address:</sf:label></th>

         <td><sf:input path="email" size="30"/> 
             <small>In case you forget something</small><br/>
             <sf:errors path="email" cssClass="error" />
            </td>
      </tr>
      <!--<start id="image_field"/>--> 
      <tr>
        <th><label for="image">Profile image:</label></th>
        <td><input name="image" type="file"/>
      </tr>
      <!--<end id="image_field"/>--> 
      <tr>
         <th></th>
         <td>
            <sf:checkbox path="updateByEmail"/>
            <sf:label path="updateByEmail" 
            >Send me email updates!</sf:label>

         </td>
      </tr>
      <tr>
         <th></th>
         <td><input name="commit" type="submit" 
                    value="I accept. Create my account." /></td>
      </tr>
   </table>
</fieldset>
</sf:form>
</div> --%>
