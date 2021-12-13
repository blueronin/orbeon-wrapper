<%@ page import="java.time.ZonedDateTime" %>

<div class="w-full max-w-sm mx-auto mt-8">
    <div class="w-full m-auto mb-8 text-primary text-center text-lg font-bold">Lets get to know you first...</div>

    <form id="user-info" class="bg-white shadow-md rounded px-4 pt-2 pb-4 mb-4" action="" method="post">
        <div class="mb-4">
            <label class="block text-gray-700 text-sm font-bold mb-3" for="organization">
                Organization/Team Name <span class="text-red-500 text-xs">*</span>
            </label>
            <input class="w-full py-2 px-3 text-gray-700" id="organization" name="organization" type="text" placeholder="Enterprise X" required />
        </div>

        <div class="mb-6">
            <label class="block text-gray-700 text-sm font-bold mb-2" for="full-name">
                Full Names <span class="text-red-500 text-xs">*</span>
            </label>
            <input class="w-full py-2 px-3 text-gray-700 mb-3 " id="full-name" name="full-name" type="text" placeholder="Sam Smith" required />
        </div>

        <div class="flex items-center justify-end">
            <button class="btn-primary hover:bg-blue-700 text-white font-bold px-4 py-2" type="submit" disabled>
                Continue...
            </button>
        </div>
    </form>
    <p class="text-center text-gray-500 text-xs">
        &copy; <%= ZonedDateTime.now().getYear() %> Basestone Inc. All rights reserved.
    </p>
</div>
