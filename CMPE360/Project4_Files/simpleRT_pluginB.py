# ----------
# Task 1: Implement Shadow Ray Casting
#
# I have initiated a shadow ray at the point where our primary ray from the camera intersects an object.
# This shadow ray heads towards the current light source being considered in this loop iteration.
# The intersection point, termed as 'point_of_intersection', was previously calculated. 
# Complete the following steps to determine if this point lies in shadow.
#
# First, compute the vector pointing from the point of intersection to the light source, named 'vector_to_light'.
# You can find the light's position in light.location.
# Subtract the intersection point's coordinates from the light's coordinates to calculate this.
# The Vector() constructor with .xyz is used to create this as an array.
#
light_vec = Vector(np.array([light.location.x - point_of_intersection.x, 
                             light.location.y - point_of_intersection.y, 
                             light.location.z - point_of_intersection.z])).xyz 

# Normalize 'vector_to_light' to obtain 'direction_to_light', representing the unit vector in the same direction.
#
light_dir = light_vec.normalized()

# Calculate the starting point of the shadow ray, named 'shadow_ray_start'.
# Remember to offset it slightly to avoid self-occlusion errors.
# Multiply 'direction_to_light' by a small scalar value 'eps' and add it to 'point_of_intersection'.
#
new_orig = hit_loc + eps*light_dir

# Use Blender's ray casting function to send the shadow ray from the intersection point towards the light.
# Capture whether the ray hits any object before reaching the light.
has_light_hit, _, _, _, _, _ = ray_cast(
    scene, new_orig, light_dir
)  # DO NOT CHANGE

# Rerun this script and render the scene to verify your results with Checkpoint 1.
# Black pixels may indicate incorrect self-occlusion handling.
# ----------

# ----------
# Task 2: Apply Blinn-Phong BRDF Model
# 
# If the shadow ray is obstructed before reaching the light, then the intersection point is in shadow,
# and the ray_cast function will return True in light_obstruction.
if has_light_hit:
    continue # Skip this light's contribution as it's obstructed.
# Otherwise, compute the color at the intersection point using the Blinn-Phong BRDF model. Let
# 'pixel_color' represent our calculated color:
# 
# pixel_color = color_diffuse + color_specular
#       color_diffuse: Diffuse light component
#       color_specular: Specular light component
#
# Diffuse light component is calculated as:
# color_diffuse = diffuse_intensity * light_intensity * (direction_to_light dot surface_normal)
#       diffuse_intensity: Intensity of diffuse light, here as diffuse_color
#       light_intensity: Light's intensity, attenuated by distance using inverse-square law
#       direction_to_light: Vector from surface point to light, here as direction_to_light
#       surface_normal: Normal vector at the surface point, here as surface_normal_vector
#
# Specular light component is calculated as:
# color_specular = specular_intensity * light_intensity
#                  * (surface_normal_vector dot half_vector)^shininess_factor
#       specular_intensity: Intensity of specular component, here as specular_color
#       light_intensity: As above
#       surface_normal_vector: As above
#       half_vector: Halfway vector between the view and light directions
#       shininess_factor: Specular hardness, here as specular_hardness
# where:
#       half_vector = normalized vector of direction_to_light + view_direction
#           direction_to_light: As above
#           view_direction: Direction from surface point to viewer, opposite of ray_direction
        
# Calculate light's intensity 'light_intensity', inversely scaled by distance
I_light = light_color / light_vec.length_squared
        
# Compute diffuse component and add to the pixel's color
#
k_diffuse = np.array(diffuse_color)
I_diffuse = k_diffuse * I_light * (light_dir.dot(hit_norm))
color += np.array(I_diffuse)
#
# Rerun this script, and render to verify the results
# ----------
# Compute specular component and incorporate it into pixel color
# ADD YOUR CODE HERE
#
k_specular = np.array(specular_color)
I_specular = k_specular * I_light * (hit_norm.dot((light_dir - ray_dir).normalized()) ** specular_hardness)
color += np.array(I_specular)
#
# Re-run this script, and render the scene to verify the results 
# ----------

# Set flag for checking light obstruction
no_light_hit = False

# ----------
# Task 3: Ambient Lighting Effect
#
# If none of the lights illuminate the object, add the ambient light component 'color_ambient' to the pixel's color
# Else, skip this step. Use the existing code as a reference to identify the variable for ambient color.
#
# color_ambient = diffuse_intensity * ambient_light_intensity
    if no_light_hit:
        k_diffuse = np.array(diffuse_color)
        I_ambient = k_diffuse * ambient_color
        color += np.array(I_ambient)
    # Original line of code:
    # pixel_color += np.zeros(3)
#
# Re-run this script, and render the scene to check your result with Checkpoint 3.
# ----------
