# ----------
        # TODO 1: Shadow Ray
        #
        # We cast a shadow ray that begins at the intersection point between our initial ray from the camera
        # and our object. The shadow ray goes towards the direction of the light that we are inspecting in
        # this current iteration of the for-loop. We declared this intersection point hit_loc above, short for
        # hit location. Finish the code below to see if this hit location is in shadow...
        #
        # First, calculate the direction vector from the hit location to the light and call it light_vec.
        # The location of the light can be accessed through light.location.
        # For x, y and z; light.location must be subtracted by the hit's location
        # To write this with the Vector(....).xyz syntax, it was written as an array.
        #
        light_vec = Vector(np.array([light.location.x - hit_loc.x, light.location.y - hit_loc.y, light.location.z - hit_loc.z])).xyz # replaced Vector(np.ones(3))
        #
        # Normalize light_vec and save that as light_dir.
        # light_dir works as the unit vector with the same direction
        #
        light_dir = light_vec.normalized() # replaced np.ones(3)
        #
        # Calculate the origin of the shadow ray: new_orig.
        # Remember to account for spurious self-occlusion!
        # The light_dir is a vector and it can be multiplied with the scalar value eps
        # Since hit_loc is the point that the ray hits, it should be checked next.
        #
        new_orig = hit_loc + eps*light_dir # replaced np.ones(3)
        #
        # Cast the shadow ray from the hit location to the light using Blender's ray cast function.
        has_light_hit, _, _, _, _, _ = ray_cast(
            scene, new_orig, light_dir
        )  # DO NOT CHANGE
        #
        # Re-run this script, and render the scene to check your result with Checkpoint 1.
        # If you see black pixels, then you might have done your check for self-occlusion wrong.
        # ----------

        # ----------
        # TODO 2: Blinn-Phong BRDF
        # 
        # If our shadow ray hits something before reaching the light, then we are in the shadow of the light,
        # and the ray_cast function above will return an appropriate boolean in has_light_hit.
        if has_light_hit:
            continue # We are in shadow, so this light will have no contribution to the color.
        # Otherwise, we calculate the color at our intersection point using the Blinn-Phong BRDF model. Let
        # I represent our color:
        # 
        # I = I_diffuse + I_specular
        #       I_diffuse: diffuse component
        #       I_specular: specular component
        #
        # The diffuse component can be calculated as:
        # I_diffuse = k_diffuse * I_light * (light_dir dot normal_dir)
        #       k_diffuse: intensity of diffuse component, in our case diffuse_color
        #       I_light: intensity of the light, light_color attenuated by inverse-square law
        #       light_dir: direction from the surface point to the light, in our case light_dir
        #       normal_dir: normal at the point on the surface, in our case hit_norm
        #
        # The specular component can be calculated as:
        # I_specular = k_specular * I_light
        #              * (normal_dir dot half_vector)^power
        #       k_specular: intensity of specular component, in our case specular_color
        #       I_light: same as above
        #       normal_dir: same as above
        #       half_vector: halfway vector between the view direction and light direction
        #       power: in our case specular_hardness
        # where:
        #       half_vector = the normalized vector of light_dir + view_dir
        #           light_dir: same as above
        #           view_dir: direction from the surface point to the viewer, the negative of ray_dir
        
        # Calculate intensity of the light, I_light, and scale it inversely by the distance
        I_light = light_color / light_vec.length_squared
        
        # Calculate diffuse component and add that to the pixel color
        #
        k_diffuse = np.array(diffuse_color)
        I_diffuse = k_diffuse * I_light * (light_dir.dot(hit_norm))
        color += np.array(I_diffuse)
        #
        # Re-run this script, and render the scene to check your result 
        # ----------
        # Calculate specular component and add that to the pixel color
        # FILL WITH YOUR CODE
        #
        k_specular = np.array(specular_color)
        I_specular = k_specular * I_light * (hit_norm.dot((light_dir - ray_dir).normalized()) ** specular_hardness)
        color += np.array(I_specular)
        #
        # Original line of code:
        # color += np.array(diffuse_color)
        #
        # Re-run this script, and render the scene to check your result 
        # ----------

        # set flag for light hit
        no_light_hit = False

    # ----------
    # TODO 3: AMBIENT
    #
    # If none of the lights hit the object, add the ambient component I_ambient to the pixel color
    # else, pass here. Look at the source code above and do some pattern matching to find the variable
    # that contains our ambient color.
    #
    # I_ambient = k_diffuse * k_ambient
    if no_light_hit:
        k_diffuse = np.array(diffuse_color)
        I_ambient = k_diffuse * ambient_color
        color += np.array(I_ambient)
        # Original line of code:
        # color += np.zeros(3)
    #
    # Re-run this script, and render the scene to check your result with Checkpoint 3.
    # ----------