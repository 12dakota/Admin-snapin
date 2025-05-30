package chat.thescholarsden

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var devicePolicyManager: DevicePolicyManager
    private lateinit var compName: ComponentName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        devicePolicyManager = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        compName = ComponentName(this, DeviceAdminReceiverImpl::class.java)

        val btnEnable = findViewById<Button>(R.id.btnEnableAdmin)
        val btnDisable = findViewById<Button>(R.id.btnDisableAdmin)

        btnEnable.setOnClickListener {
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, compName)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Enable device admin for additional features")
            startActivity(intent)
        }

        btnDisable.setOnClickListener {
            devicePolicyManager.removeActiveAdmin(compName)
            Toast.makeText(this, "Device Admin Deactivated", Toast.LENGTH_SHORT).show()
        }
    }
}