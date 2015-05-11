package it.polito.mobile.polijobplacement;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.LinkedList;


public class profile_student extends ActionBarActivity {

    private Student1 student1;
    Button btnfinish;
    ImageButton btnAdd;
    ImageButton btnDel;
    EditText txtName;
    EditText txtGender;
    EditText txtAge;
    EditText txtSpeciality;
    ListView list;
    ParseUser user= ParseUser.getCurrentUser();
    LinkedList<EduBackground> edu=new LinkedList<EduBackground>();
    BaseAdapter myBaseAdapter;
    EditText txtdegree;
    EditText txtmajor;
    EditText txtuni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        txtName=(EditText)findViewById(R.id.editStuName);
        txtAge=(EditText)findViewById(R.id.editStuAge);
        txtGender=(EditText)findViewById(R.id.editStuGender);
        txtSpeciality=(EditText)findViewById(R.id.editStuSpecialty);
        btnfinish=(Button)findViewById(R.id.btnProfileComplete);
        if ((boolean)user.get("isProfileCompleted"))
        {
            int count=(int)user.get("EduSize");
            String[] tempM=((String)user.get("Majors")).split("=");
            String[] tempU=((String)user.get("Universities")).split("=");
            String[] tempD=((String)user.get("Degrees")).split("=");
            for (int i=0;i<count;i++)
            {
                edu.add(new EduBackground(tempD[i],tempU[i],tempM[i]));
            }
            student1 =new Student1((String)user.get("Name"),(String)user.get("Age"),(String)user.get("Gender"),(String)user.get("Specialty"),edu);
            txtName.setText(student1.name);
            txtGender.setText(student1.gender);
            txtAge.setText(student1.age);
            txtSpeciality.setText(student1.specialty);

        }

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listDataUpdate())
                {
                    Toast.makeText(profile_student.this,"there are still blanks need to be filled ", Toast.LENGTH_LONG).show();
                    return;
                }
                String name=txtName.getText().toString();
                String age=txtAge.getText().toString();
                String gender=txtGender.getText().toString();
                String specialty=txtSpeciality.getText().toString();
                if (name.equals("")||age.equals("")||gender.equals("")||specialty.equals(""))
                {
                    Toast.makeText(profile_student.this,"there are still blanks need to be filled ", Toast.LENGTH_LONG).show();
                    return;
                }
                student1 =new Student1(name,age ,gender,specialty,edu);

                user.put("Name", student1.name);
                user.put("Gender", student1.gender);
                user.put("Age", student1.age);
                user.put("Specialty", student1.specialty);
                user.put("EduSize",edu.size());
                StringBuilder tempM=new StringBuilder();
                StringBuilder tempU=new StringBuilder();
                StringBuilder tempD=new StringBuilder();
                for (int i=0;i<edu.size();i++)
                {
                    tempM.append(edu.get(i).getMajor()+"=");
                    tempD.append(edu.get(i).getDegree()+"=");
                    tempU.append(edu.get(i).getUniversity()+"=");
                }
                user.put("Degrees",tempD.toString());
                user.put("Majors",tempM.toString());
                user.put("Universities",tempU.toString());
                user.put("isProfileCompleted", true);
                user.put("isProfileUncompletedAlertNeverShown",true);

                try {
                    user.save();
                    Toast.makeText(profile_student.this,"success", Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(profile_student.this,"fail to update the info at the server side", Toast.LENGTH_LONG).show();
                }

            }
        });



        list=(ListView)findViewById(R.id.eduList);
        myBaseAdapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return edu.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView==null) {
                    convertView= getLayoutInflater().inflate(R.layout.listitem_edu,parent,false);
                }
                txtdegree=(EditText)convertView.findViewById(R.id.txtEduDegree);

                txtmajor=(EditText)convertView.findViewById(R.id.txtEduMajor);

                txtuni=(EditText)convertView.findViewById(R.id.txtEduUniversity);


                txtdegree.setText(edu.get(position).degree);
                txtmajor.setText(edu.get(position).major);
                txtuni.setText(edu.get(position).university);
                btnDel=(ImageButton)convertView.findViewById(R.id.imageBtnDeleteEdu);
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edu.remove(position);
                        list.setAdapter(myBaseAdapter);
                    }
                });
                return convertView;
            }
        };
        list.setAdapter(myBaseAdapter);
        btnAdd=(ImageButton)findViewById(R.id.imageBtnAddEdu);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listDataUpdate();
                if (edu.size()>0)
                {
                    EduBackground temp= edu.getLast();
                    if (temp.major.equals("")||temp.university.equals("")||temp.degree.equals(""))
                    {
                        Toast.makeText(profile_student.this,"Please fill the previous one first", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                edu.add(new EduBackground("", "", ""));
                list.setAdapter(myBaseAdapter);

            }
        });



    }

    private boolean listDataUpdate()
    {
        boolean hasblanks=false;
        int count=list.getAdapter().getCount();
        for (int i = 0; i < count; i++) {
            View mView = list.getChildAt(i);
            txtdegree=(EditText)mView.findViewById(R.id.txtEduDegree);
            txtmajor=(EditText)mView.findViewById(R.id.txtEduMajor);
            txtuni=(EditText)mView.findViewById(R.id.txtEduUniversity);

            edu.set(i,new EduBackground(txtdegree.getText().toString(),txtuni.getText().toString(),txtmajor.getText().toString()));
            if (edu.get(i).degree.equals("")||edu.get(i).major.equals("")||edu.get(i).university.equals(""))
            {
                hasblanks=true;
            }
        }
        return hasblanks;
    }



}
