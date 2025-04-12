import {register} from "./request/user.js";
import {sendVerifiedCode} from "./request/email.js";
import Toast from "./util/toast.js";

document.addEventListener('DOMContentLoaded', function () {


    document.getElementById('sendCodeBtn').addEventListener('click', async function () {

        const email = document.getElementById('email').value;

        if (!email) {
            alert('请输入邮箱地址');
            return;
        }

        if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email)) {
            alert('邮箱格式不正确');
            return;
        }

        try {
            const res = await sendVerifiedCode({email: email});

            if (res.code === 200 && res.data.code === 200) {
                Toast.show('验证码已发送，请查收');
                startCountdown();
            } else {
                Toast.err(res.data.msg || '验证码发送失败');
            }
        } catch (error) {
            Toast.err('验证码发送失败');
        }
    });

    function startCountdown() {
        let seconds = 60;
        const btn = document.getElementById('sendCodeBtn');
        btn.disabled = true;

        const timer = setInterval(function () {
            seconds--;
            btn.textContent = seconds + '秒后重试';

            if (seconds <= 0) {
                clearInterval(timer);
                btn.disabled = false;
                btn.textContent = '获取验证码';
            }
        }, 1000);
    }

    // Register form submission
    document.getElementById('submitBtn').addEventListener('click', async function (e) {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const code = document.getElementById('code').value;
        const name = document.getElementById('name').value;

        if (!email || !password || !code || !name) {
            Toast.err('请填写所有必填项');
            e.preventDefault();
            return;
        }

        const res = await register({
            email,
            password,
            code,
            name,
            sex: document.querySelector('input[name="sex"]:checked').value,
            signature: document.getElementById('signature').value
        })
        console.log("reg res", res)

        if (res.code === 200 && res.data.code === 200) {
            Toast.show('注册成功')
            localStorage.setItem('userData',res.data.data)
            setTimeout(() => {
                window.location.href = '/index.jsp';
            }, 1000);
        } else {
            Toast.err(res.data.msg)

        }

    });

    document.getElementById('backBtn').addEventListener('click', function () {
        window.history.back();
    });
});