package com.example.workoutadvisor;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExercises
{
    public ArrayList<String> getWorkouts(String workoutType){
        ArrayList<String> workout = new ArrayList<String>();
        if(workoutType.equals("PUSH-1")){
            workout.add("Wall Assisted HandStand Pushups ");
            workout.add("HandStand Shoulder Taps ");
            workout.add("Wall Walks");
            workout.add("Parallel Bar Dips/Weighted Dips");
            workout.add("Archer Pushups");
            workout.add("Straight Bar Dips");
            workout.add("Inner Chest Pushups");
        }
        else if(workoutType.equals("PUSH-2")){
            workout.add("1 Rep Max Pushups with Weights\n");
            workout.add("Max Reps Pushups(Regular)\n");
            workout.add("Bench-Press\n");
            workout.add("Diamond Pushups\n");
            workout.add("Squats\n");
        }
        else if(workoutType.equals("PULL-1")){
            workout.add("Explosive Pullups\n");
            workout.add("MuscleUp negative\n");
            workout.add("Weighted Pullups\n");
            workout.add("Australian Bicep Curls\n");
            workout.add("Australian Rows\n");
        }
        else if(workoutType.equals("PULL-2")){
            workout.add("1 Rep Max Weighted PullUps\n");
            workout.add("PullUps Max Reps(1 Set Max)\n");
            workout.add("Deadlift\n");
            workout.add("Wide Grip Lat Pulldown\n");
            workout.add("V-Bar Lat Pulldown\n");
        }
        else if(workoutType.equals("LEGS")){
            workout.add("Pistol Squats\n");
            workout.add("100 Squats(<8mins)\n");
            workout.add("Jumping Squats\n");
            workout.add("Lateral Jumps\n");
            workout.add("Romanian Deadlift\n");
            workout.add("Calf Raises\n");
        }
        else if(workoutType.equals("CHEST")){
            workout.add("Incline Bench Press-\n");
            workout.add("Dumbbell Press\n");
            workout.add("Incline Bench Dumbbell Fly\n");
            workout.add("Bench Press\n");
            workout.add("Chest Dips\n");
            workout.add("Decline Dumbbell Press\n");
        }
        else if(workoutType.equals("TRICEPS")){
            workout.add("Diamond Pushups\n");
            workout.add("Rope Push-Down\n");
            workout.add("Tricep Kickback\n");
            workout.add("Cable SkullCrushers\n");
            workout.add("Overhead Tricep Extensions\n");
        }
        else if(workoutType.equals("BICEPS")){
            workout.add("Barbell Curls\n");
            workout.add("Incline Dumbell Curl\n");
            workout.add("Chin-Ups\n");
            workout.add("EZ-Bar Preacher Curl\n");
            workout.add("21s\n");
        }
        else if(workoutType.equals("BACK")){
            workout.add("Wide Grip Lat Pulldown\n");
            workout.add("PullUps\n");
            workout.add("Barbell Rows\n");
            workout.add("Dumbell Rows\n");
            workout.add("V Grip Lat Pulldown\n");
            workout.add("Deadlift\n");
        }
        else{
            workout.add("EXERCISES COMMING SOON.......\n");
        }
        return workout;
    }

    public ArrayList<String> getDescription(String workoutType){
        ArrayList<String> workout = new ArrayList<String>();
        if(workoutType.equals("PUSH-1")){
            workout.add("To complete a wall handstand push up:\n\n" +
                    "1-Face a wall in a standing position.\n\n" +
                    "2-Kick your feet up so you’re in a handstand position against a wall.\n\n" +
                    "3-Squeeze your abs, glutes and thigh muscles.\n\n" +
                    "4-Lower yourself toward the ground as far as possible.\n\n" +
                    "5-Push back up and repeat.\n\n" +
                    "Note: If you’re worried about falling on your head, put a yoga block or a pillow under your head. \n" +
                    "Also, if you find it too difficult/scary to kick your feet up against a wall, simply face opposite the wall and walk your feet up instead.\n");
            workout.add("You can perform this exercise either back facing wall or facing the wall.\n\n" +
                    "1-Get into a wall supported handstand position (either back or forward facing)\n\n" +
                    "2-Lean your bodyweight towards one hand by tilting your legs towards the desired supporting hand\n\n" +
                    "3-Lift up your free hand quickly tap your supporting hand’s shoulder\n\n" +
                    "4-Repeat on the other side\n");
            workout.add("1-Start in the “up push-up” position with your feet against the wall behind you.\n\n" +
                    "2-Start walking your feet up the wall, while at the same time walking your hands back towards the wall.\n\n" +
                    "3-Walk up until you are in a complete handstand position as flat to the wall as you can.\n\n" +
                    "4-Keep your core tight and back straight.\n\n" +
                    "5-To finish, walk your hands away from the wall and your feet down the wall until you are back in the push-up position.\n\n");
            workout.add("1-If there are two grip settings, take the narrower grip for triceps emphasis (the wider grip dips are for chest emphasis).\n\n" +
                    "2-With a firm grip on the narrow parallel bars, keep your head up and chest up, while looking straight ahead as you begin the rep.\n\n" +
                    "3-Let your feet hang straight underneath your body or behind you with bent knees.\n\n" +
                    "4-Begin to lower your body while keeping your elbows close together and your upper arms at your sides. Stop when your upper arms are parallel to the floor and the elbow joint reaches a 90 degree angle.\n\n" +
                    "5-Focusing on your triceps, push yourself back up to the top position until your arms are straight, maintaining an upright posture through the entire rep.\n\n" +
                    "6-Repeat for the desired number of reps\n");
            workout.add("1-Get into an all-fours position with your knees and toes flexed and in contact with the floor. Your hips should be over your knees. Position your hands wider than your shoulders. Your hands should be outwardly rotated so your fingers are pointed away from your body and a straight line is formed from your shoulders to your hands.\n\n" +
                    "2-Grip the ground with your hands and rotate your shoulders outward to engage your lats. Straighten your legs to lift your knees off of the ground so you end up in a high plank position. Place your legs together or keep them hip-width apart.\n\n" +
                    "3-Pre-tension your shoulders and hips with a good inhale and exhale while engaging your core. Squeeze your quads and glutes. Your chin should remain tucked throughout the movement, as if you were holding an egg under your chin. All repetitions should begin from this starting position.\n\n" +
                    "4-Maintain a strong core position and shift your upper body toward your right side. Pull your right chest toward your right hand as you bend your right elbow. Allow your right shoulder and hand to inwardly rotate as you lower into the push-up position.\n\n" +
                    "5-As you lower toward the right side, allow your left arm to straighten. Your left arm should be fully extended, forming a straight line from your shoulder to your hand. Your right elbow should finish close to your ribcage.\n");
            workout.add("1-Step up on a straight bar dip station (if possible) and position your hands with a pronated grip (thumbs facing).\n\n" +
                    "2-Initiate the dip by unlocking the elbows and slowly lowering the body until the forearms are almost parallel with the floor.\n\n" +
                    "3-Control the descent to parallel and then drive back to the starting position by pushing through the palms.\n\n" +
                    "4-Repeat for the desired number of repetitions.\n");
            workout.add("Watch this video till 2:32\n\n" +
                    "1- Use a bench or set a bet till your waist position \n\n" +
                    "2-Put your hands in such a way that you come in a incline position \n\n" +
                    "3-Push yourself up with your hannds \n\n" +
                    "4-Squeeze your chest and your hands inwards you should feel tension on your inner chest\n\n" +
                    "5-Repeat this for a good 8-10 repss\n\n");
        }
        else if(workoutType.equals("PUSH-2")){
            workout.add("1-Get into an all-fours position with your knees and toes flexed and in contact with the floor. Your hips should be over your knees, and your hands should be slightly wider than your shoulders.\n\n" +
                    "2-Grip the ground with your hands and rotate your shoulders outward to engage your lats.\n\n" +
                    "3-Straighten your legs to lift your knees off of the ground so you end up in a plank position. Your legs should be hip-width apart or slightly wider.\n\n" +
                    "4-Pre-tension your shoulders and hips while engaging your core. Your ribs should be down and your pelvis should be slightly tucked.\n\n" +
                    "5-Squeeze your quads and glutes. Your chin should remain tucked throughout the movement as if you were holding an egg under your chin.\n\n" +
                    "6-Once in a strong position, have a partner gently place a weight plate on your lower to mid-back. The weight plate should be in a stable position before beginning the push-up.\n\n" +
                    "7-While maintaining your alignment and a strong core, pull your chest toward your hands by bending your elbows. Your shoulder blades should retract as you lower toward the ground.\n\n" +
                    "8-Lower your body until your upper arms are even with your back. Your elbows should be 45 degrees away from your body, and your wrists should be under your elbows.\n\n" +
                    "9-Pause for a second at the bottom.\n\n" +
                    "10-While maintaining your alignment, initiate the upward movement by squeezing your chest and straightening your elbows. Your shoulder blades should protract as you push to the top of the movement.\n\n" +
                    "11-Finish the movement by squeezing your chest and triceps.\n\n" +
                    "12-Repeat for your desired number of repetitions.");
            workout.add("1. Get down on all fours, placing your hands slightly wider than your shoulders. \n\n" +
                    "2. Straighten your arms and legs. \n\n" +
                    "3. Lower your body until your chest nearly touches the floor. \n\n" +
                    "4. Pause, then push yourself back up. \n\n" +
                    "5. Repeat.");
            workout.add("1-Lie on your back on a flat bench. Grip a barbell with hands slightly wider than shoulder-width.\n\n" +
                    "2-Press your feet firmly into the ground and keep your hips on the bench throughout the entire movement.\n\n" +
                    "3-Slowly lift bar off rack, if using, and lower the bar to the chest, allowing elbows to bend out to the side.\n\n" +
                    "4-Stop lowering when elbows are just below the bench. Press feet into the floor as you push the bar back up to return to starting position.\n\n" +
                    "5-Perform 5-10 reps, depending on weight used. Perform up to three sets.");
            workout.add("1-Get on all fours with your hands together under your chest. \n\n" +
                    "2-Position your index fingers and thumbs so they’re touching, forming a diamond shape.\n\n" +
                    "3-Extend your arms so that your body is elevated and forms a straight line from your head to your feet.\n\n" +
                    "4-Lower your chest towards your hands, ensuring you don’t flare your elbows out to the sides and keeping your back flat. \n\n" +
                    "5-Stop just before your chest touches the floor\n\n" +
                    "5-Then push back up to the starting position.\n\n" +
                    "6-Repeat");
            workout.add("1-Initiate the movement by sending the hips back as if you’re sitting back into an invisible chair. \n\n" +
                    "2-Bend knees to lower down as far as possible with chest lifted in a controlled movement. \n\n" +
                    "3-Keep lower back neutral. Press through heels to stand back up to starting position. \n\n" +
                    "4-Repeat.\n\n" +
                    "Aim to get thighs at least parallel to the floor.To do this,squat down so your thighs are even with your knees.\n" +
                    "If mobility allows, lower further. As you come back up, make sure hips are set right under your ribs— you don’t want your hips to pull too far back");
        }
        else if(workoutType.equals("PULL-1")){
            workout.add("1-Start in a hanging pull-up position.\n\n" +
                    "2-As you begin the pull-up, accelerate your body so you get some momentum.\n\n" +
                    "3-At the top of the pull-up, keep the momentum going and as your head and shoulders pass the bar.\n\n" +
                    "4-As your body starts to return to the ground and control your descent back to a hanging position.\n\n" +
                    "Now for the level- up question: “I can only do a few pull-ups, how do I get better?”\n\n" +
                    "This is a challenging movement and will take some practice.");
            workout.add("Many people will have a hard time during this phase of the muscle-up progression at first.\n\n" +
                    "It takes a lot of control but mastering this piece will bring everything together. \n\n" +
                    "1-After your dip, you will keep your thumbs tucked nice and close to your chest. \n\n" +
                    "2-Start to slowly let your body slide back and out of that dip position. \n\n" +
                    "3-As you do so the big fight will be keeping your elbows in a tight bent position. ");
            workout.add("Perform a regular pullup but with weights on\n\n" +
                    "1-You can either use a dip belt\n\n" +
                    "2-Or you can use dumbell and hold them with your legs\n\n" +
                    "This is a hard move so you should be able to do at-least 8-10 pullups \n\n");
            workout.add("1-Bring a Bar to your waist or stomach level\n\n" +
                    "2-Go under the bar and hold with both your hands\n\n" +
                    "3-Now pull yourself up with your hands\n\n" +
                    "4-Try to use more of your biceps and squeeze them\n\n" +
                    "5-Go down and repeat this movement for a good 5-reps or more\n\n");
            workout.add("Australian Rows\n");
        }
        else if(workoutType.equals("PULL-2")){
            workout.add("1 Rep Max Weighted PullUps\n");
            workout.add("PullUps Max Reps(1 Set Max)\n");
            workout.add("Deadlift\n");
            workout.add("Wide Grip Lat Pulldown\n");
            workout.add("V-Bar Lat Pulldown\n");
        }
        else if(workoutType.equals("LEGS")){
            workout.add("Pistol Squats\n");
            workout.add("100 Squats(<8mins)\n");
            workout.add("Jumping Squats\n");
            workout.add("Lateral Jumps\n");
            workout.add("Romanian Deadlift\n");
            workout.add("Calf Raises\n");
        }
        else if(workoutType.equals("CHEST")){
            workout.add("Incline Bench Press-\n");
            workout.add("Dumbbell Press\n");
            workout.add("Incline Bench Dumbbell Fly\n");
            workout.add("Bench Press\n");
            workout.add("Chest Dips\n");
            workout.add("Decline Dumbbell Press\n");
        }
        else if(workoutType.equals("TRICEPS")){
            workout.add("Diamond Pushups\n");
            workout.add("Rope Push-Down\n");
            workout.add("Tricep Kickback\n");
            workout.add("Cable SkullCrushers\n");
            workout.add("Overhead Tricep Extensions\n");
        }
        else if(workoutType.equals("BICEPS")){
            workout.add("Barbell Curls\n");
            workout.add("Incline Dumbell Curl\n");
            workout.add("Chin-Ups\n");
            workout.add("EZ-Bar Preacher Curl\n");
            workout.add("21s\n");
        }
        else if(workoutType.equals("BACK")){
            workout.add("Wide Grip Lat Pulldown\n");
            workout.add("PullUps\n");
            workout.add("Barbell Rows\n");
            workout.add("Dumbell Rows\n");
            workout.add("V Grip Lat Pulldown\n");
            workout.add("Deadlift\n");
        }
        else{
            workout.add("EXERCISES COMMING SOON.......\n");
        }
        return workout;
    }

    public ArrayList<String> getLink(String workoutType){
        ArrayList<String> workout = new ArrayList<String>();
        if(workoutType.equals("PUSH-1")){
            workout.add("LPvzXFRW5R0");
            workout.add("r8WwpGQlq7U");
            workout.add("VpuoE246W1Y");
            workout.add("dX_nSOOJIsE");
            workout.add("A0r8ploEnZY");
            workout.add("TSQP6rZqjiM");
            workout.add("hFPtVbLwrSQ");
        }
        else if(workoutType.equals("PUSH-2")){
            workout.add("Q_FnrwhkdpU");
            workout.add("JyCG_5l3XLk");
            workout.add("4aVy2Xj6wYs");
            workout.add("pD3mD6WgykM");
            workout.add("MVMNk0HiTMg");
        }
        else if(workoutType.equals("PULL-1")){
            workout.add("kbeiEWxGAMw");
            workout.add("_gXECajymcY");
            workout.add("HuuyDNGrCI8");
            workout.add("vByJfI8acOw");
            workout.add("al3nLD8vDnY");
        }
        else if(workoutType.equals("PULL-2")){
            workout.add("1 Rep Max Weighted PullUps\n");
            workout.add("PullUps Max Reps(1 Set Max)\n");
            workout.add("Deadlift\n");
            workout.add("Wide Grip Lat Pulldown\n");
            workout.add("V-Bar Lat Pulldown\n");
        }
        else if(workoutType.equals("LEGS")){
            workout.add("Pistol Squats\n");
            workout.add("100 Squats(<8mins)\n");
            workout.add("Jumping Squats\n");
            workout.add("Lateral Jumps\n");
            workout.add("Romanian Deadlift\n");
            workout.add("Calf Raises\n");
        }
        else if(workoutType.equals("CHEST")){
            workout.add("Incline Bench Press-\n");
            workout.add("Dumbbell Press\n");
            workout.add("Incline Bench Dumbbell Fly\n");
            workout.add("Bench Press\n");
            workout.add("Chest Dips\n");
            workout.add("Decline Dumbbell Press\n");
        }
        else if(workoutType.equals("TRICEPS")){
            workout.add("Diamond Pushups\n");
            workout.add("Rope Push-Down\n");
            workout.add("Tricep Kickback\n");
            workout.add("Cable SkullCrushers\n");
            workout.add("Overhead Tricep Extensions\n");
        }
        else if(workoutType.equals("BICEPS")){
            workout.add("Barbell Curls\n");
            workout.add("Incline Dumbell Curl\n");
            workout.add("Chin-Ups\n");
            workout.add("EZ-Bar Preacher Curl\n");
            workout.add("21s\n");
        }
        else if(workoutType.equals("BACK")){
            workout.add("Wide Grip Lat Pulldown\n");
            workout.add("PullUps\n");
            workout.add("Barbell Rows\n");
            workout.add("Dumbell Rows\n");
            workout.add("V Grip Lat Pulldown\n");
            workout.add("Deadlift\n");
        }
        else{
            workout.add("EXERCISES COMMING SOON.......\n");
        }
        return workout;
    }
    public ArrayList<Integer> getImage(String workoutType){
        ArrayList<Integer> workout = new ArrayList<Integer>();
        if(workoutType.equals("PUSH-1")){
            workout.add(R.drawable.p1);
            workout.add(R.drawable.p2);
            workout.add(R.drawable.p3);
            workout.add(R.drawable.p4);
            workout.add(R.drawable.p5);
            workout.add(R.drawable.p6);
            workout.add(R.drawable.p7);
        }
        else if(workoutType.equals("PUSH-2")){
            workout.add(R.drawable.p21);
            workout.add(R.drawable.p22);
            workout.add(R.drawable.p23);
            workout.add(R.drawable.p25);
            workout.add(R.drawable.p24);
        }
        else if(workoutType.equals("PULL-1")){
            workout.add(R.drawable.pu1);
            workout.add(R.drawable.pu2);
            workout.add(R.drawable.pu3);
            workout.add(R.drawable.pu4);
            workout.add(R.drawable.pu5);
        }
        else if(workoutType.equals("PULL-2")){
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
        }
        else if(workoutType.equals("LEGS")){
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
        }
        else if(workoutType.equals("CHEST")){
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
        }
        else if(workoutType.equals("TRICEPS")){
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
        }
        else if(workoutType.equals("BICEPS")){
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
        }
        else if(workoutType.equals("BACK")){
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
            workout.add(R.drawable.pu5);
        }
        else{
            workout.add(R.drawable.workoutimage);
        }
        return workout;
    }

}


