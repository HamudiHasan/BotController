package tech.firebricks.btbotcontroller;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class ControllerActivity extends AppCompatActivity {
    Button btnOn, btnOff, btnDis;

    ImageButton btnUp;
    ImageButton btnDown;
    ImageButton btnLeft;
    ImageButton btnRight;

    Button btnFL;
    Button btnFR;
    Button btnBL;
    Button btnBR;

    Button btnG;
    Button btnR;

    Button btnbodyUp;
    Button btnbodyDown;

    Button btnMoveUp;
    Button btnMoveDown;
    ImageButton btnStop;

    Button btnGripStop;
    Button btnhandStop;
    Button btnbodyStop;

    SeekBar brightness;
    TextView lumn;
    String address = null;
    ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        progress =  new ProgressDialog(this);
        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS); //receive the address of the bluetooth device

        setContentView(R.layout.activity_controller);

        btnDis = (Button)findViewById(R.id.button4);

        btnUp = (ImageButton) findViewById(R.id.btn_up);
        btnDown = (ImageButton)findViewById(R.id.btn_down);
        btnLeft = (ImageButton)findViewById(R.id.btn_left);
        btnRight = (ImageButton)findViewById(R.id.btn_right);
        btnStop = (ImageButton)findViewById(R.id.btn_center);

        btnFL = (Button)findViewById(R.id.btn_fl);
        btnFR = (Button)findViewById(R.id.btn_fr);
        btnBL = (Button)findViewById(R.id.btn_bl);
        btnBR = (Button)findViewById(R.id.btn_br);

        btnG = (Button)findViewById(R.id.btn_grip);
        btnR = (Button)findViewById(R.id.btn_release);

        btnbodyUp = (Button)findViewById(R.id.btn_bdy_move_up);
        btnbodyDown = (Button)findViewById(R.id.btn_bdy_move_down);

        btnMoveUp = (Button)findViewById(R.id.btn_hnd_move_up);
        btnMoveDown = (Button)findViewById(R.id.btn_hnd_move_down);

        btnGripStop = (Button)findViewById(R.id.btn_grip_stop);
        btnhandStop = (Button)findViewById(R.id.btn_hnd_stop);
        btnbodyStop = (Button)findViewById(R.id.btn_body_stop);




        brightness = (SeekBar)findViewById(R.id.seekBar);
        lumn = (TextView)findViewById(R.id.lumn);

        new ConnectBT().execute(); //Call the class to connect

        //commands to be sent to bluetooth
        btnUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendString("F");      //method to turn on
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("B");   //method to turn off
            }
        });

        btnLeft .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("L");   //method to turn off
            }
        });

        btnRight .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("R");   //method to turn off
            }
        });

        btnFL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("G");   //method to turn off
            }
        });

        btnFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("I");   //method to turn off
            }
        });

        btnBL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("H");   //method to turn off
            }
        });

        btnBR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("J");   //method to turn off
            }
        });

        btnG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("C");   //method to turn off
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("D");   //method to turn off
            }
        });

        btnbodyUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("M");   //method to turn off
            }
        });

        btnbodyDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("N");   //method to turn off
            }
        });

        btnMoveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("P");   //method to turn off
            }
        });

        btnMoveDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("Q");   //method to turn off
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("S");   //method to turn off
            }
        });

        btnGripStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendString("E");      //method to turn on
            }
        });

        btnhandStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("K");   //method to turn off
            }
        });

        btnbodyStop .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sendString("O");   //method to turn off
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect(); //close connection
            }
        });

        brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser==true)
                {
                    lumn.setText(String.valueOf(progress));
                    try
                    {
                        btSocket.getOutputStream().write(String.valueOf(progress).getBytes());
                    }
                    catch (IOException e)
                    {

                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout

    }

    private void sendString(String s)
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write(s.toString().getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }


    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(ControllerActivity.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
