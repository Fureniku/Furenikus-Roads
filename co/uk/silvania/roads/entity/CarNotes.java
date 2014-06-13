package co.uk.silvania.roads.entity;

public class CarNotes {
	
	//TODO The following is just a list of notes/ideas I've had for cars.
	/*
	 * Cars are very expensive to make, and are by no means an early-game thing. That being said, because of how they'll work, you will be able to buy one with a single-stock shelf from FlenixCities.
	 * 
	 * Firstly, cars are never items. They are built into the world as a growing entity, and only break when destroyed. There is no "placing" a car, which sets it far aside from any other car mod.
	 * Secondly, the production will be an incredibly expensive process. The machines to build the car will be expensive, and the parts will also be quite expensive. This is the GregTech of transport.
	 * Finally, you'll have to maintain the car. Fuel is obvious, plus water and oil, but there will be wear and tear parts, as well as a tracked mileage. Your car will randomly break down. More on that later.
	 * 
	 * 
	 * == PRODUCTION ==
	 * Production will happen on a production line. You'll need conveyors and robots to assemble the car, across a number of machines.
	 * Actual parts list is as follows:
	 * Door (2 or 4 for most cars)
	 * Door Glass
	 * Wheel
	 * Brakes
	 * Tyre
	 * Axle
	 * Small Panel
	 * Large Panel (Roof, bonnet)
	 * Softtop (for convertable)
	 * Windscreen
	 * Windscreen Wipers
	 * Rear Windscreen
	 * Back Door (Varies depending on car)
	 * Exhaust (With Cat. Converter)
	 * Steering Wheel
	 * Steering Column
	 * Large Cogs
	 * Seats (various amounts)
	 * Interior carpet/leather (Can be coloured by using dye, else will match exterior colour)
	 * Headlights
	 * Indicators
	 * Break Lights
	 * Tail Lights
	 * Reverse Lights
	 * Pedals
	 * Gearstick/PRNDL stick
	 * Lots of piping
	 * Lots of wiring
	 * Battery
	 * Central Mirror
	 * Wing Mirrors
	 * Locking mechanism (paired with two keys)
	 * 
	 * Engine:
	 * - Air Filter
	 * - Radiator
	 * - Oil Reserve
	 * - Fuel Injector
	 * - Fuel Pressure Regulator
	 * - Cylinders (Combustion chamber, piston - more = better engine)
	 * - Turbocharger
	 * - Valves
	 * - Pistons
	 * - Alternator
	 * - Brake Fluid Reservoir
	 * - Fan Belt
	 * - Crank Shaft
	 * - Fly Wheel
	 * - Spark Plug
	 * - Pipes
	 * Lots more I've not thought of yet
	 * 
	 * Cars are built a little like FCF food; totally modular and each one is different. There are a number of preset shapes:
	 * - Small hatchback
	 * - Estate
	 * - 4x4
	 * - People Carrier
	 * - Small Van
	 * - Large Van
	 * - Coupe
	 * - Sedan
	 * - Luxury
	 * - Sportscar
	 * 
	 * And these below, which have much less configuration options:
	 * - Minibus
	 * - Bus
	 * - Truck (Optional load)
	 * - Flatbed Truck
	 * - Recovery Vehicle
	 * 
	 * Once you decide a shape and metal, you can build the chassis. You decide the colour later. Metal choice affects both durability and max speed (Heavier metals are stronger but slower)
	 * Next, you build the engine and put it in. Engine power is decided here, and affects the car's max speed
	 * Next you add things like the wheels to the car. Choice of these affects handling speeds
	 * After that you add electronics. Battery etc are required, but there are upgrades:
	 * - Headlights to see at night
	 * - Windscreen wipers to see in the rain
	 * - Power Steering for better handling
	 * - Stereo to listen to choons
	 * - Dashboard to render speed, milage, revometer and fuel on-screen while driving
	 * - If FCT is installed, Windscreen can be updated to a UITech screen, displaying the above on it as well as highlighting hazards and offering directions.
	 * 
	 * There are other things which can be upgraded too:
	 * - Brakes for better stopping power
	 * - Upgrade from manual to automatic for easier driving
	 * - Wheels for better handling
	 * - Tyres for better accelerating/braking
	 * - Tinted windows for partial gangsta levels
	 * - Hydraulic wheels for 100% Gangstarisation
	 * - Larger (louder) exhaust and subwoofers for a fake gangsta level.
	 * 
	 * As well as maybe a few easter egg upgrades:
	 * - Weapons, concealed like a 007 car. Only works for the sportscar and luxury car.
	 * - Putting the large exhaust and subwoofer on a small hatchback changes car texture to be quite chavvy.
	 * 
	 * 
	 * == DRIVING ==
	 * Driving will be pretty simple. Holding W increases acceleration as an integer; letting go of it slowly decreases it again. Pressing S decreases it much quicker, and shows the break lights.
	 * For manual cars, keys will change gears up/down when you like. Automatic will do it, you guessed it, automatically.
	 * There will be keys for indicators. Mostly decorative but work with autoroad* systems.
	 * Pressing S while still will do the opposite of accelerating, and put you into reverse. Reverse is much slower.
	 * A and D will turn your steering wheel. They do NOT straife like when you are walking, they rotate the wheels, which set the direction of the car.
	 * A will negate the int, D will add to it, up to a value of +/- 210. Everything between -30 and 30 is "Neutral", you won't steer; otherwise you steer more or less depending on the value.
	 * This means when you are going fast, it's harder to turn quickly. The speed in which you turn is decided by the steering mechanism you build the car with.
	 * 
	 * So to sum up, two integers: Acceleration and Steer. Acceleration positive is forward, negative is backwards, steering positive is right, negative is left.
	 * 
	 * More advanced things happen too. For example, when night comes, you can turn on headlights to see better by lighting in front of the car. When it rains, your windscreen will blur until you put the windscreen wipers on.
	 * 
	 * 
	 * 
	 * == MAINTANENCE ==
	 * Your car can break in many, many ways. Some affect your drive, others don't. Some are more common than others.
	 * 
	 * - You can get a flat tyre. Odds doubled when driving on anything that isn't road, or stone-based (eg dirt, gravel etc)
	 * - You can run out of petrol. Once your tank hits 0, you'll loose max speed quickly until you can't move at all.
	 * - Your clutch can fail, causing you to lose gears. This has a 1 in 5/6 chance on gear, the lower the gear you lose, the worse impact it has.
	 * - Blown Bulb. Affects how light headlights are
	 * - Flat Battery. Prevents you from starting the car once stopped. Only happens when not driving.
	 * - Broken/Missing Exhaust. Makes your car louder (Not good for sneaking)
	 * - Blown Gasket - expensive and hard to fix
	 * - Your windows can break. No physical effect, just cosmetic - but with a broken windscreen, anyone can get in the car whether it is locked or not.
	 * 
	 * Some things can be fixed at the roadside with a wrench and the replacement part. Some things have to be taken to a garage to fix.
	 * 
	 * == OTHER ==
	 * - To use a car, first unlock it with the key (If needed) with shift-rightclick with said key. Then, get in, then right-click with the key again to begin driving.
	 * - Once in the car, the player themselves are actually invisible. Instead, a model identical to player is rendered, and uses the players Minecraft skin. By doing this, the player
	 *   model will render correctly, with only their head turning, and their hands will grip and animate with the steering wheel.
	 * - Cars can be hotwired if you don't have the key.
	 * - Crashing into things will hurt you (or it). You can break glass by driving through it.
	 * - Driving through water will destroy your car. 
	 * - Everything possible will graphically animate. Steering wheel will turn, wheels will spin and turn left/right, windscren wipers move, the doors, boot and bonnet can all open.
	 * - Eventually, I'd love the mirrors to work. Someone made a mod which added a mirror to the player's HUD - Need to look at the src.
	 * 
	 */

}
