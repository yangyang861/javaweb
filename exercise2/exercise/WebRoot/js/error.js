//����window�����setInterval��������ÿ��1000ms�Զ�ִ������changeLeaveT ime����
window.setInterval(changeLeaveTime, 1000);
//�Զ��庯��changeLeaveTime
function changeLeaveTime() {
	/*��ȡid=leaveTime�ı�ǩԪ�ض���,������innerText���ԣ���ֵת��Ϊ����*/
	var time = parseInt(document.getElementById("leaveTime").innerText);
	time=time - 1;
	if (time == 0) {
	window. location.href = "login.html" ;
	} else {
	//��ȡid=leaveTime�ı�ǩԪ�ض���,����innerText����
	document . getElementById("leaveTime"). innerText = time;
	}
}


 
 
 