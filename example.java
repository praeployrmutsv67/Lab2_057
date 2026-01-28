public class MainActivity extends AppCompatActivity implements View.OnClickListener {
String TAG;
TextView txtv;
EditText etn1,etn2 ;
Button btn1,btn2,btn3,btn5,btn6 ;
FirebaseDatabase database = FirebaseDatabase.getInstance();
DatabaseReference myRef = database.getReference(" ");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etn1 = findViewById(R.id.etn1);
        etn2 = findViewById(R.id.etn2);
        txtv = findViewById(R.id.txtv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);




        myRef.setValue("Hello, World!");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onClick(View view) {
        int num1 = Integer.parseInt(etn1.getText().toString());
        int num2 = Integer.parseInt(etn2.getText().toString());
        if (view.getId()==btn1.getId()){
            String sum = String.valueOf(num1+num2);
            myRef.setValue(sum);
            txtv.setText(sum);
        } else if (view.getId()==btn2.getId()) {
            String sum = String.valueOf(num1 / num2);
            myRef.setValue(sum);
            txtv.setText(sum);
        }
    }
    public void BtnClear (View view){
        etn1.setText("");
        etn2.setText("");
        txtv.setText("");
    }
}