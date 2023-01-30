<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<title>Game Board</title>
</head>
<body>
	<div>
       <div>
			<h3>Welcome to Board Game</h3>
				  <div class="text-center">
				 	<c:set var="turn">${gameStatus.currentPlayer}</c:set>
				 	<c:if test="${feedbackMessage == null}">
				  		<p><fmt:message key="player.turn"/>: ${gameStatus.players[turn].name} </p>
				  	</c:if>
				  </div>
				  <table >
				  	<tbody>
				  		    <tr>
				  		    	<td rowspan="2">${gameStatus.board.stonesInPit(1,0)}</td>
					  		    <c:forEach var="i" begin="1" end="${gameStatus.board.playingpits}">
	      							<td>
	      								<div>${gameStatus.board.stonesInPit(1,i)}</div>
	      								<c:choose>
		      								<c:when test="${gameStatus.currentPlayer eq 1 and gameStatus.board.stonesInPit(1,i)>0}">
				      							<form action="board/play.htm" method="post">
				      								<input type="hidden" name="pitIndex" value="${i}">
		        									<button type="submit"> <fmt:message key="pick.stones"/> </button>
		    									</form>
			      							</c:when>
			      							<c:otherwise>
			      								<button type="submit"  disabled="disabled"> <fmt:message key="pick.stones"/> </button>
			      							</c:otherwise>
		      							</c:choose>
	      							</td>
	    						</c:forEach>
	    						<td rowspan="2">${gameStatus.board.stonesInPit(0,0)}</td>
    						</tr>
    						<tr>
					  		    <c:forEach var="i" begin="1" end="${gameStatus.board.playingpits}" >
					  		     	<c:set var="decr" value="${gameStatus.board.playingpits+1-i}"/>
	      							<td>
	      								<div>${gameStatus.board.stonesInPit(0,decr)}</div>
	      								<c:choose>
		      								<c:when test="${gameStatus.currentPlayer eq 0 and gameStatus.board.stonesInPit(0,decr)>0}">
			      								<form action="${baseUrl}/board/play.htm" method="post">
				      								<input type="hidden" name="pitIndex" value="${decr}">
				      								<button type="submit" > <fmt:message key="pick.stones"/> </button>
				      							</form>
			      							</c:when>
			      							<c:otherwise>
			      								<button type="submit"  disabled="disabled"> <fmt:message key="pick.stones"/> </button>
			      							</c:otherwise>
		      							</c:choose>
		      						</td>
	    						</c:forEach>
    						</tr>
				  	</tbody>
				  </table>
			<c:if test="${feedbackMessage != null}">
				<div class="text-center">
					<div><c:out value="${feedbackMessage}"/></div>
					<a href="${baseUrl}"><fmt:message key="new.game"/></a>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>